## 1

```
 @Entity
 public class HibernatePersistedMovie {

     @Id
     private String id;

     @Column // what does this line do?
     private String title;
 }
 ```
 
## 2
 
```
 // what's wrong here?
 @Transactional
 @Component
 public class MovieService {

     public void updateMovie(String movieId) {
           Movie movie = movieRepository.find(movieId);
           movie.setName("ahdjahdkas");
           movieRepository.save(movie);
     }
 }
 ```
## 3
```
  // what's wrong here?
  @Entity(name = "movies")
  public class HibernatePersistedMovie {

      @Id
      private String id;

      @Column
      private String title;
  }
 
  entityManager.createQuery("SELECT m FROM HibernatePersistedMovie");
 ```

## 4
```
  // what's wrong here?
  @Entity
  public class HibernatePersistedMovie {

      @Id
      private String id;

      @Column
      private String title;
      
      @OneToOne
      private Customer customer;
  }
 
  @Entity
  public class HibernatePersistedCustomer {
 
      @Id
      private Long id;
 
      @Column
      private String name;
      
      @OneToOne
      private Movie movie;
  }
```
## 5

```
  // what's wrong here?
  @Entity
  public class HibernatePersistedCustomer {
 
      @Id
      private Long id;
 
      @Column
      private String name;
      
      @OneToMany
      private List<Movie> movies;
  }
```

## 6

```
  // what's wrong here?
  @Entity
  public class HibernatePersistedCustomer {
 
      // ... 
      
      @ManyToMany
      private List<Customer> customers;
  }
  
   @Entity
   public class HibernatePersistedCustomer {
   
      // ...
      
      @ManyToMany
      private List<Movie> movies;
    }
```

## 7
```
// what's wrong here?
  @Entity
  public class HibernatePersistedCustomer {
 
      // ... 
      
      @OneToMany
      private List<Movie> movies;
  }
  
   
   List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c");
   for (Customer customer : customers {
       for (Movie movie: customer.getMovies() {
            // ...
       }
   }
```