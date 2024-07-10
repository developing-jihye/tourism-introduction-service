package tours.tourism;

import jakarta.persistence.*;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private String phoneNumber;

    private String city;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String website;

    private double rationg;

}
