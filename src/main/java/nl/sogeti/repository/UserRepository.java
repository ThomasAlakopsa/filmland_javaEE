package nl.sogeti.repository;

import nl.sogeti.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional(Transactional.TxType.SUPPORTS)
public class UserRepository {

    @PersistenceContext(unitName = "filmlandPU")
    private EntityManager em;

    public Optional<User> find(Long id) {

        User user = em.find(User.class, id);

        return user != null ? Optional.of(user) : Optional.empty();
    }

    public List<User> getAllUsers(){
        List<User> foo = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        System.out.println("in repo" + foo);
        return foo;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        em.remove(em.getReference(User.class, id));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(User user) {
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        em.persist(user);
    }

    public Optional<User> findUserWithEmail(String email) {
        List<User> userList = (em.createQuery("SELECT u FROM User u WHERE u.email LIKE: custEmail")
                   .setParameter("custEmail", email).getResultList());

        return userList.isEmpty() ? Optional.empty() : Optional.of(userList.get(0));
    }
}
