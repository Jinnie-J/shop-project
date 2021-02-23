package com.maker.shop.service;

import com.maker.shop.dto.CartDTO;
import com.maker.shop.dto.PageRequestDTO;
import com.maker.shop.dto.PageResultDTO;
import com.maker.shop.dto.PickListDTO;
import com.maker.shop.entity.*;
import com.maker.shop.repository.CartRepository;
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

    private final PickListRepository pickListRepository; //찜한상품
    private final CartRepository cartRepository; //장바구니

    //찜한상품 part
    @Override
    public Long registerPL(PickListDTO pickdto) {
        log.info(pickdto);

        PickList pickList=dtoToEntity(pickdto);
        pickListRepository.save(pickList);

        return pickdto.getPickNo();
    }

    @Override
    public PageResultDTO<PickListDTO, Object[]> getPickList(PageRequestDTO pageRequestDTO,String email) {

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
    public PickListDTO getPL(Long pickNo) {
        //해당 상품 조회페이지로 이동
        return null;
    }

    @Override
    public void removePL(Long pickNo) {
        pickListRepository.deleteById(pickNo);
    }

    //장바구니 part
    @Override
    public Long registerCart(CartDTO cartDTO) {
        log.info(cartDTO);

        Cart cart=dtoToEntity(cartDTO);
        cartRepository.save(cart);

        return cartDTO.getCno();
    }

    @Override
    public PageResultDTO<CartDTO, Object[]> getCartList(PageRequestDTO pageRequestDTO, String email) {
        log.info(pageRequestDTO);

        Pageable pageable= pageRequestDTO.getPageable(Sort.by("cno").descending());
        Page<Object[]> result=cartRepository.getCartListPage(pageable,email);
        Function<Object[], CartDTO> fn=( arr-> entitiesToDTO(
                (Cart)arr[0],
                (Member)arr[1],
                (Product)arr[2],
                (ProductSize)arr[3],
                (List<ProductImage>)(Arrays.asList((ProductImage)arr[4]))
        ));

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public CartDTO getCart(Long cno) {
        return null;
    }

    @Override
    public void removeCart(Long cno) {
        cartRepository.deleteById(cno);

    }


}
