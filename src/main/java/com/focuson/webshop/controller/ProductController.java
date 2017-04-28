package com.focuson.webshop.controller;

import com.focuson.webshop.bean.product.ProductType;
import com.focuson.webshop.bean.protocol.PageView;
import com.focuson.webshop.bean.protocol.entity.ResponseStatus;
import com.focuson.webshop.bean.protocol.request.CreateProductTypeRequest;
import com.focuson.webshop.bean.protocol.request.DeleteRequest;
import com.focuson.webshop.bean.protocol.request.ProductTypeScrollRequest;
import com.focuson.webshop.bean.protocol.response.BaseResponse;
import com.focuson.webshop.bean.protocol.response.ProductTypeScrollResponse;
import com.focuson.webshop.service.product.ProductTypeService;
import com.focuson.webshop.service.product.QueryResult;
import com.focuson.webshop.service.product.repository.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @author Focuson created on 17-3-6.
 */

@Slf4j
@RestController
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BaseResponse> create(@RequestBody CreateProductTypeRequest request) {

        try {
            ProductType productType = new ProductType(request.getName(), request.getNote());
            if (request.getParentId() != null){
                ProductType parent = productTypeService.find(ProductType.class, request.getParentId());
                productType.setParent(parent);
            }
            productTypeService.save(productType);
        } catch (Exception ex) {
            log.error("Error: ", ex);
            return new ResponseEntity<>(new BaseResponse(ResponseStatus.FAILURE, "失败"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BaseResponse(ResponseStatus.SUCCESS, "成功"), HttpStatus.OK);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String delete(@RequestBody DeleteRequest request) {
        try {
            List<Integer> ids = request.getIds();
            Object[] dIds = new Object[ids.size()];
            for (int i = 0; i < ids.size(); i++) {
                dIds[i] = ids.get(i);
            }
            productTypeService.delete(ProductType.class, dIds);
        } catch (Exception ex) {
            log.error("Error: ", ex);
            return "{\"status\": \"FAILURE\"}";
        }
        return "{\"status\": \"SUCCESS\"}";
    }

    @RequestMapping(value = "/getScrollData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BaseResponse> getScrollData(@Valid ProductTypeScrollRequest request, BindingResult bindingResult) {
        try {
            QueryResult<ProductType> queryResult =
                    productTypeService.getScrollData(ProductType.class, request.getFirstIndex(), request.getMaxResult());

            PageView<ProductType> pageView = new PageView<>();
            pageView.setTotal(queryResult.getTotalRecords());
            pageView.setRows(queryResult.getResultList());
            return new ResponseEntity<>(new ProductTypeScrollResponse(ResponseStatus.SUCCESS, "成功", pageView), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error: ", ex);
            throw new RuntimeException("Error: ", ex);
        }
    }

//    @RequestMapping(value = "/getScrollData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public ResponseEntity<BaseResponse> getScrollData(@RequestBody ProductTypeScrollRequest request) {
//        try {
//            QueryResult<ProductType> queryResult =
//                    productTypeService.getScrollData(ProductType.class, request.getFirstIndex(), request.getMaxResult());
//            ProductTypeQueryData data = new ProductTypeQueryData();
//            data.setTotalRecords(queryResult.getTotalRecords());
//            data.setProductTypes(queryResult.getResultList());
//            return new ResponseEntity<>(new ProductTypeScrollResponse("OK", "成功", data), HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error("Error: ", ex);
//            throw new RuntimeException("Error: ", ex);
//        }
//    }


    @RequestMapping(value = "/getScrollDataOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getScrollDataOrder(@RequestBody ProductTypeScrollRequest request) {
        try {

            System.out.println("--------------------------------------------");
            QueryResult<ProductType> queryResult = productTypeService.getScrollData(ProductType.class);
            for (ProductType product : queryResult.getResultList()) {
                System.out.println(product.getTypeId() + "\t" + product.getName() + "\t" + product.getNote());
            }

            System.out.println("--------------------------------------------");
            queryResult =
                    productTypeService.getScrollData(ProductType.class, request.getFirstIndex(), request.getMaxResult());
            for (ProductType product : queryResult.getResultList()) {
                System.out.println(product.getTypeId() + "\t" + product.getName() + "\t" + product.getNote());
            }

            System.out.println("--------------------------------------------");
            queryResult = productTypeService.getScrollData(ProductType.class, "o.visible=?1",
                    new Object[]{true}, request.getFirstIndex(), request.getMaxResult());
            for (ProductType product : queryResult.getResultList()) {
                System.out.println(product.getTypeId() + "\t" + product.getName() + "\t" + product.getNote());
            }


            System.out.println("--------------------------------------------");
            LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();
            orderBy.put("categoryId", "DESC");
            queryResult = productTypeService.getScrollData(ProductType.class, "o.visible=?1",
                    new Object[]{true}, request.getFirstIndex(), request.getMaxResult(), orderBy);
            for (ProductType product : queryResult.getResultList()) {
                System.out.println(product.getTypeId() + "\t" + product.getName() + "\t" + product.getNote());
            }
        } catch (Exception ex) {
            log.error("Error: ", ex);
            return "{\"status\": \"FAILURE\"}";
        }
        return "{\"status\": \"SUCCESS\"}";
    }
}
