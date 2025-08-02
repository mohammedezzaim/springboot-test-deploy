package ma.zyn.app.dao.facade.core.commande;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commande.CommandeItem;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CommandeItemDao extends AbstractRepository<CommandeItem,Long>  {

    List<CommandeItem> findByCommandeId(Long id);
    int deleteByCommandeId(Long id);
    long countByCommandeCode(String code);


}
