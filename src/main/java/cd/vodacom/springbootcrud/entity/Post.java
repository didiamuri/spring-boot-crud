package cd.vodacom.springbootcrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Post title is required")
    private String title;

    @NotBlank(message = "Post content is required")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @NotBlank(message = "Status is required")
    @Column(nullable = false)
    private String status = "active";

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
}
