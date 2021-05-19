package nl.sogeti.repository;

import nl.sogeti.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
public class UserRepository {

    @PersistenceContext(unitName = "filmlandPU")
    private EntityManager em;

    public User find(Long id) {return em.find(User.class , id);}

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {em.remove(em.getReference(User.class, id));}

    @Transactional(Transactional.TxType.REQUIRED)
    public User create(User user){
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        em.persist(user);
        return user;
    }

    public User findByEmail(String email){
        return (User) em.createQuery("SELECT u FROM User u WHERE u.email LIKE: custEmail")
                .setParameter("custEmail", email)
                .getSingleResult();
    }
}
