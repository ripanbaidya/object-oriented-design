# Delivery framework

![](/public//images/delivery-framework.png)

### 1. Requirements (~5 minutes)

_The goal is to turn a minimal, usually single-sentence prompt (e.g., “Design Tic Tac Toe”) into an explicit, completely unambiguous specification before designing._

- **Pacing Strategy:** Spend the first 1–2 minutes asking focused questions across four specific themes to prime your thinking.
- **The Four Essential Themes:**
    - **Primary Capabilities:** What core operations must this system support?
    - **Rules and Completion:** What exact conditions define success, failure, or when the system stops/transitions state?
    - **Error Handling:** How should the system respond when inputs or actions are invalid?
    - **Scope Boundaries:** Explicitly define what is **In Scope** (core logic, business rules) vs. **Out of Scope** (UI, storage, networking, concurrency, extensibility) to eliminate scope creep.

- **Whiteboard Requirement Spec Example (Tic Tac Toe):**

    1. Two players alternate placing X and O on a 3x3 grid.
    2. A player wins by completing a row, column, or diagonal.
    3. The game ends in a draw if all nine cells are filled with no winner.
    4. Invalid moves should be rejected (placing on an occupied cell, acting after the game is over).
    5. The system should provide a way to query current game state and reset the game.

    - _Out of Scope:_ UI/rendering layer, AI opponent/move suggestions, networked multiplayer, variable board sizes ($N \times N$ grids), and undo/redo functionality.

### 2. Entities and Relationships (~3 minutes)

_Map out the macro-structure and ownership boundaries of the system before getting lost in individual class definitions._

- **Identify Entities (The Noun Filter):** Scan requirements for meaningful nouns.
    - _Rule of thumb:_ If it maintains a changing state or enforces rules, it's an **Entity**. If it is just static information attached to something else, it belongs as a **field** inside another class.

- **Define Relationships:** Determine how components interact by answering:
    - Which entity is the **orchestrator** driving the main workflow?
    - Which entities own durable state?
    - How do they depend on each other (`has-a`, `uses`, `contains`)?
    - Where do specific rules logically live?

- **Whiteboard Notation Rule:** Do _not_ get bogged down by rigid, formal UML diagrams (composition, visibility, cardinality symbols). Use simple boxes, labels, and arrows to clearly communicate the flow of ownership.
- **Visual Structure Example (Tic Tac Toe):**
    - _Entities:_ `Game`, `Board`, `Player`
    - _Relationships:_ `Game -> Board` and `Game -> Player (2x)`

### 3. Class Design (~10–15 minutes)

_Transform the structural diagram into actual class outlines using a top-down approach, starting with the orchestrator._

- **The Guiding Principle (Encapsulation / "Tell, Don't Ask"):** Keep rules packaged tightly with the entity that owns the relevant state. Objects must manage their own state and expose behavioral methods, rather than exposing raw getters for external callers to make decisions.
    - _Workflow/Lifecycle rules_ (e.g., "can this operation run right now?") $\rightarrow$ belong in the **orchestrator**.
    - _Data-specific rules_ (e.g., "is this cell already occupied?") $\rightarrow$ belong in the **specific data-owning class**.

- **Deriving State from Requirements:** Map responsibilities directly to in-memory tracking.
    - _Example (Game Class State):_
        - `board : Board`
        - `playerX : Player`
        - `playerO : Player`
        - `currentPlayer : Player`
        - `state : GameState` (`IN_PROGRESS`, `WON`, `DRAW`)
        - `winner : Player?` (null if no winner)

- **Deriving Behavior from Requirements:** Identify what explicit operations or queries the outside world must call.
    - _Example (Game Class Methods):_
        - `makeMove(player, row, col) -> bool`
        - `getCurrentPlayer() -> Player`
        - `getGameState() -> GameState`
        - `getWinner() -> Player?`

### 4. Implementation (~10 minutes)

_Translate class designs into clean, readable code or indentation-based pseudo-code. Always ask the interviewer for their language/detail preference before writing._

- **Step 1: The Happy Path First:** Implement the straight-line, linear execution sequence where everything works perfectly (inputs received $\rightarrow$ sequence of steps $\rightarrow$ internal calls $\rightarrow$ state changes/returns).
- **Step 2: Edge Cases:** Enumerate and write explicit logic for failure modes (invalid inputs, illegal operations, out-of-range values, state violations).
- **The Complete Pseudo-Code Example From the Article:**

    ```text
    makeMove(player, row, col)
        if state != IN_PROGRESS
            return false
        if player != currentPlayer
            return false
        if !board.canPlace(row, col)
            return false

        board.placeMark(row, col, player.mark)

        if board.checkWin(row, col, player.mark)
            state = WON
            winner = player
        else if board.isFull()
            state = DRAW
        else
            currentPlayer = (player == playerX) ? playerO : playerX

        return true

    ```

- **Design Pattern Warning:** Design patterns (Singleton, Factory, Builder) are highly impactful _only_ when required. Do not overengineer by forcing patterns where they don't add genuine value.
- **Verification (1–2 minutes):** Step through a concrete, non-trivial example tick-by-tick to verify initial state, subsequent operations, and state changes (e.g., tracing `makeMove(X, 0, 0)` $\rightarrow$ check board state $\rightarrow$ verify turn switches to `O`). If you spot a bug during this walkthrough, fix it immediately on the spot.

### 5. Extensibility (~5 minutes)

_Address interviewer-led twists and "what-if" extensions to prove the architecture is robust and adaptable without needing a rewrite._

- **Expectations by Engineering Level:**
    - _Junior:_ Little to no extensibility discussion.
    - _Mid-Level:_ One or two minor follow-up twists.
    - _Senior:_ A sequential chain of multiple "what-if" scenarios.

- **Isolate Mutations:** The key to passing this section is isolating state changes so additions don't break existing code.
- **The Complete "Undo" Extension Example:**
    - _Question:_ "How would you add undo functionality?"
    - _Answer:_ Point out that because all state transitions flow strictly through a single action method (`makeMove`), you can introduce a command history stack. Record the previous state onto the stack before modifying anything. An `undo()` method simply pops the stack and reverts the state, requiring zero structural changes to the rest of the application.

- **Execution Strategy:** Stay high-level. Do not rewrite massive blocks of code; instead, point directly to the boundaries in your design where the new feature plugs in cleanly.

## References

- https://www.hellointerview.com/learn/low-level-design/in-a-hurry/delivery