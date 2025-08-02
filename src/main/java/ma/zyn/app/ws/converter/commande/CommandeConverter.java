package  ma.zyn.app.ws.converter.commande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.commun.EtatCommandeConverter;
import ma.zyn.app.bean.core.commun.EtatCommande;
import ma.zyn.app.ws.converter.commande.CommandeItemConverter;
import ma.zyn.app.bean.core.commande.CommandeItem;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.commande.Commande;
import ma.zyn.app.ws.dto.commande.CommandeDto;

@Component
public class CommandeConverter {

    @Autowired
    private EtatCommandeConverter etatCommandeConverter ;
    @Autowired
    private CommandeItemConverter commandeItemConverter ;
    private boolean etatComande;
    private boolean commandeItems;

    public  CommandeConverter() {
        init(true);
    }

    public Commande toItem(CommandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Commande item = new Commande();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getTotal()))
                item.setTotal(dto.getTotal());
            if(StringUtil.isNotEmpty(dto.getTotalPayer()))
                item.setTotalPayer(dto.getTotalPayer());
            if(StringUtil.isNotEmpty(dto.getDateCommande()))
                item.setDateCommande(DateUtil.stringEnToDate(dto.getDateCommande()));
            if(this.etatComande && dto.getEtatComande()!=null)
                item.setEtatComande(etatCommandeConverter.toItem(dto.getEtatComande())) ;


            if(this.commandeItems && ListUtil.isNotEmpty(dto.getCommandeItems()))
                item.setCommandeItems(commandeItemConverter.toItem(dto.getCommandeItems()));


        return item;
        }
    }


    public CommandeDto toDto(Commande item) {
        if (item == null) {
            return null;
        } else {
            CommandeDto dto = new CommandeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getTotal()))
                dto.setTotal(item.getTotal());
            if(StringUtil.isNotEmpty(item.getTotalPayer()))
                dto.setTotalPayer(item.getTotalPayer());
            if(item.getDateCommande()!=null)
                dto.setDateCommande(DateUtil.dateTimeToString(item.getDateCommande()));
            if(this.etatComande && item.getEtatComande()!=null) {
                dto.setEtatComande(etatCommandeConverter.toDto(item.getEtatComande())) ;

            }
        if(this.commandeItems && ListUtil.isNotEmpty(item.getCommandeItems())){
            commandeItemConverter.init(true);
            commandeItemConverter.setCommande(false);
            dto.setCommandeItems(commandeItemConverter.toDto(item.getCommandeItems()));
            commandeItemConverter.setCommande(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.commandeItems = value;
    }
    public void initObject(boolean value) {
        this.etatComande = value;
    }
	
    public List<Commande> toItem(List<CommandeDto> dtos) {
        List<Commande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CommandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CommandeDto> toDto(List<Commande> items) {
        List<CommandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Commande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CommandeDto dto, Commande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEtatComande() == null  && dto.getEtatComande() != null){
            t.setEtatComande(new EtatCommande());
        }else if (t.getEtatComande() != null  && dto.getEtatComande() != null){
            t.setEtatComande(null);
            t.setEtatComande(new EtatCommande());
        }
        if (dto.getEtatComande() != null)
        etatCommandeConverter.copy(dto.getEtatComande(), t.getEtatComande());
        if (dto.getCommandeItems() != null)
            t.setCommandeItems(commandeItemConverter.copy(dto.getCommandeItems()));
    }

    public List<Commande> copy(List<CommandeDto> dtos) {
        List<Commande> result = new ArrayList<>();
        if (dtos != null) {
            for (CommandeDto dto : dtos) {
                Commande instance = new Commande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EtatCommandeConverter getEtatCommandeConverter(){
        return this.etatCommandeConverter;
    }
    public void setEtatCommandeConverter(EtatCommandeConverter etatCommandeConverter ){
        this.etatCommandeConverter = etatCommandeConverter;
    }
    public CommandeItemConverter getCommandeItemConverter(){
        return this.commandeItemConverter;
    }
    public void setCommandeItemConverter(CommandeItemConverter commandeItemConverter ){
        this.commandeItemConverter = commandeItemConverter;
    }
    public boolean  isEtatComande(){
        return this.etatComande;
    }
    public void  setEtatComande(boolean etatComande){
        this.etatComande = etatComande;
    }
    public boolean  isCommandeItems(){
        return this.commandeItems ;
    }
    public void  setCommandeItems(boolean commandeItems ){
        this.commandeItems  = commandeItems ;
    }
}
