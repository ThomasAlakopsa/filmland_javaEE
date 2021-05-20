package nl.sogeti.repository;

import nl.sogeti.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional(Transactional.TxType.SUPPORTS)
public class UserRepository {

    @PersistenceContext(unitName = "filmlandPU")
    private EntityManager em;

    public Optional<User> find(Long id) {

        User user = em.find(User.class, id);

        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        em.remove(em.getReference(User.class, id));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public User create(User user) {
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        em.persist(user);
        return user;
    }

//    public User findByEmail(String email){
//        return (User) em.createQuery("SELECT u FROM User u WHERE u.email LIKE: custEmail")
//                .setParameter("custEmail", email)
//                .getSingleResult();
//    }

    public Optional<User> findUserWithEmail(String email) {
        try {
            User user = (User) em.createQuery("SELECT u FROM User u WHERE u.email LIKE: custEmail")
                    .setParameter("custEmail", email).getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
