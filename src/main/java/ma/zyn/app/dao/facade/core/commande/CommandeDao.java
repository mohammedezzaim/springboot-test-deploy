package ma.zyn.app.dao.facade.core.commande;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commande.Commande;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commande.Commande;
import java.util.List;


@Repository
public interface CommandeDao extends AbstractRepository<Commande,Long>  {
    Commande findByCode(String code);
    int deleteByCode(String code);

    List<Commande> findByEtatComandeCode(String code);
    List<Commande> findByEtatComandeId(Long id);
    int deleteByEtatComandeId(Long id);
    int deleteByEtatComandeCode(String code);
    long countByEtatComandeCode(String code);

    @Query("SELECT NEW Commande(item.id,item.code) FROM Commande item")
    List<Commande> findAllOptimized();

}
