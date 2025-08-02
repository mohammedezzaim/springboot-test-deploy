package  ma.zyn.app.ws.dto.commande;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.commun.EtatCommandeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandeDto  extends AuditBaseDto {

    private String code  ;
    private BigDecimal total  ;
    private BigDecimal totalPayer  ;
    private String dateCommande ;

    private EtatCommandeDto etatComande ;

    private List<CommandeItemDto> commandeItems ;


    public CommandeDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }


    public BigDecimal getTotalPayer(){
        return this.totalPayer;
    }
    public void setTotalPayer(BigDecimal totalPayer){
        this.totalPayer = totalPayer;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateCommande(){
        return this.dateCommande;
    }
    public void setDateCommande(String dateCommande){
        this.dateCommande = dateCommande;
    }


    public EtatCommandeDto getEtatComande(){
        return this.etatComande;
    }

    public void setEtatComande(EtatCommandeDto etatComande){
        this.etatComande = etatComande;
    }



    public List<CommandeItemDto> getCommandeItems(){
        return this.commandeItems;
    }

    public void setCommandeItems(List<CommandeItemDto> commandeItems){
        this.commandeItems = commandeItems;
    }



}
