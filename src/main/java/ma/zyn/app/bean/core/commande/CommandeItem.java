package ma.zyn.app.bean.core.commande;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "commande_item")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="commande_item_seq",sequenceName="commande_item_seq",allocationSize=1, initialValue = 1)
public class CommandeItem  extends BaseEntity     {




    private BigDecimal prix = BigDecimal.ZERO;

    private BigDecimal quantite = BigDecimal.ZERO;

    private Commande commande ;


    public CommandeItem(){
        super();
    }

    public CommandeItem(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="commande_item_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande")
    public Commande getCommande(){
        return this.commande;
    }
    public void setCommande(Commande commande){
        this.commande = commande;
    }
    public BigDecimal getPrix(){
        return this.prix;
    }
    public void setPrix(BigDecimal prix){
        this.prix = prix;
    }
    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandeItem commandeItem = (CommandeItem) o;
        return id != null && id.equals(commandeItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

