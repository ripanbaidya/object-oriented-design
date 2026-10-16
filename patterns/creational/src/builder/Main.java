package builder;

public class Main {
    static void main() {

        // Simple builder pattern
        Post post = new Post.Builder()
                .title("Spring Boot")
                .content("Learn Spring Boot basics.")
                .author("Alice")
                .build();

        // Generic builder pattern
        GenericPost genericPost = GenericBuilder.of(GenericPost::new)
                .with(GenericPost::setTitle, "Java Basics")
                .with(GenericPost::setContent, "Learn Java programming.")
                .with(GenericPost::setAuthor, "Bob")
                .build();

        System.out.println(post);
        System.out.println(genericPost);
    }
}