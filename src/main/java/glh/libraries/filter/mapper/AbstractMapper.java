package glh.libraries.filter.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AbstractMapper <E,D>{


    @Autowired
    ModelMapper mapper;

    Class<E> entityClass;

    Class<D> DTOClass;

    public AbstractMapper(Class<E> entityClass, Class<D> DTOClass){
        this.entityClass = entityClass;
        this.DTOClass = DTOClass;
    }

    public D mapEntityToDTO(E entity){
        return mapper.map(entity, DTOClass);
    }

    public E mapDTOtoEntity(D dto){
        return mapper.map(dto, entityClass);
    }

    public List<D> mapListEntitiesToDTOs(List<E> entities){
        return entities.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    }

    public List<E> mapListDTOsToEntities(List<D> dtos){
        return dtos.stream().map(this::mapDTOtoEntity).collect(Collectors.toList());
    }

}
