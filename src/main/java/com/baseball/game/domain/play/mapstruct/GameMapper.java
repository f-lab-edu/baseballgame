package com.baseball.game.domain.play.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.baseball.game.domain.play.dto.GameDto;
import com.baseball.game.domain.play.dto.entity.GameEntity;
import com.baseball.global.domain.GenericMapper;

@Mapper(componentModel = "spring")
public interface GameMapper extends GenericMapper<GameDto, GameEntity> {

    @Mappings(
            {
                    @Mapping(source = "comAnswer", target = "computerAnswer"),
                    @Mapping(target = "playerAnswer", ignore = true),
                    @Mapping(source = "gameGuessEntity", target = "guessDto"),
                    @Mapping(target = "historyData", ignore = true)
            }
    )
    @Override
    GameDto toDto(GameEntity gameEntity);


    @Mappings(
            {
                    @Mapping(source = "computerAnswer", target = "comAnswer"),
                    @Mapping(source = "guessDto", target = "gameGuessEntity")
            }
    )
    @Override
    GameEntity toEntity(GameDto gameDto);

//    @Override
//    @Mappings({
//            @Mapping(source = "guessDto", target = "gameGuessEntity")
//    })
//    void updateFromDto(GameDto dto, GameEntity entity);
}
