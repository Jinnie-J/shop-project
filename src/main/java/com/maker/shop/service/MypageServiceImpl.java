package com.maker.shop.service;

import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.PickListDTO;
import com.maker.shop.entity.*;
import com.maker.shop.repository.PickListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class MypageServiceImpl implements  MypageService{

    private final PickListRepository pickListRepository;

    @Override
    public Long register(PickListDTO pickdto) {
        log.info(pickdto);

        PickList pickList=dtoToEntity(pickdto);
        pickListRepository.save(pickList);

        return pickList.getPickNo();
    }

    @Override
    public PageResultDTO<PickListDTO, Object[]> getList(PageRequestDTO pageRequestDTO,String email) {

        log.info(pageRequestDTO);
        //member,product,productimage 조인 ..
        Pageable pageable= pageRequestDTO.getPageable(Sort.by("pickNo").descending());
        Page<Object[]> result=pickListRepository.getPickListPage(pageable,email);
        Function<Object[],PickListDTO> fn = ( arr-> entitiesToDTO(
                (PickList)arr[0],
                (Member)arr[1],
                (Product)arr[2],
                (List<ProductImage>)(Arrays.asList((ProductImage)arr[3]))
        ));
        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PickListDTO get(Long pickNo) {
        return null;
    }

    @Override
    public void removeWithReplies(Long pickNo) {

    }
}
