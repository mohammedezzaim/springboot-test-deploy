package  ma.zyn.app.dao.specification.core.commande;

import ma.zyn.app.dao.criteria.core.commande.CommandeCriteria;
import ma.zyn.app.bean.core.commande.Commande;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CommandeSpecification extends  AbstractSpecification<CommandeCriteria, Commande>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicateBigDecimal("total", criteria.getTotal(), criteria.getTotalMin(), criteria.getTotalMax());
        addPredicateBigDecimal("totalPayer", criteria.getTotalPayer(), criteria.getTotalPayerMin(), criteria.getTotalPayerMax());
        addPredicate("dateCommande", criteria.getDateCommande(), criteria.getDateCommandeFrom(), criteria.getDateCommandeTo());
        addPredicateFk("etatComande","id", criteria.getEtatComande()==null?null:criteria.getEtatComande().getId());
        addPredicateFk("etatComande","id", criteria.getEtatComandes());
        addPredicateFk("etatComande","code", criteria.getEtatComande()==null?null:criteria.getEtatComande().getCode());
    }

    public CommandeSpecification(CommandeCriteria criteria) {
        super(criteria);
    }

    public CommandeSpecification(CommandeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
