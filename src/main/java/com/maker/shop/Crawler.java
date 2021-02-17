package com.maker.shop;

import com.maker.shop.dto.ProductDTO;
import com.maker.shop.dto.ProductImageDTO;
import com.maker.shop.dto.ProductSizeDTO;
import com.maker.shop.service.ProductService;
import com.maker.shop.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/crawler")
@RequiredArgsConstructor
@Log4j2
public class Crawler {

    private final ProductService productService;
    public static List<String> list = new ArrayList<>();

    private static final String[] productList = {
            "1186,FLADBA1U19"
    };

    @GetMapping("/insert")
    public void insert() {
        productDetailCrawl();
    }

    //crawler.pnoCrawler();

        /* pnoCrawler 사용시
        try {
            FileWriter ad = new FileWriter("C:\\upload\\1111(부츠)_women.txt");

            for (String str : list) {
                ad.write("\"" + str + "\"\n");
            }
            ad.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/


    //모델명 크롤링
    /*public void pnoCrawler() {

        try {
            for (int PageIndex = 1; PageIndex < 150; PageIndex++) {
                driver.get("https://www.folderstyle.com/shop/Pr_List.aspx?BrandCD=&CateIdx=1111&BrandCDs=&PageSize=20&CurrentPage="+PageIndex+"&SortType=new&PriceCodes=&ColCodes=&SizeCodes=&CateIdxs=");
                Thread.sleep(4000);
                List<WebElement> productImg = driver.findElements(By.cssSelector("dl > dt > p > a"));
                //List<WebElement> img = driver.findElements(By.cssSelector("dl > dt > p > span.thumb_logo > img"));

                System.out.println("==================================");

                //모든 상품(element) 조회하는 for loop
                for (int i = 0; i < productImg.size() - 1; i++) {
                    //String[] brand = img.get(i).getAttribute("src").split("_");
                    String[] cate = productImg.get(i).getAttribute("href").split("\'");

                    list.add(cate[1] + "," + cate[3]);
                    System.out.println(cate[1] + "," + cate[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }*/

    //상세페이지 크롤링
    public void productDetailCrawl() {
        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        for (int i = 0; i < productList.length; i++) {
            // Chrome 드라이버 인스턴스 설정
            driver = new ChromeDriver();
            try {

                String cate = productList[i].replace("\"", "").split(",")[0];
                String pno = productList[i].replace("\"", "").split(",")[1];
                driver.get("https://www.folderstyle.com/Shop/PR_Detail.aspx?CateIdx=" + cate + "&StyleCode=" + pno + "&ColCode=19");
                Thread.sleep(3000);

                //이름
                WebElement titleElement = driver.findElement(By.className("info_tit"));
                //가격
                WebElement priceElement = driver.findElement(By.id("ProductPrice"));
                Long price = Long.parseLong(priceElement.getText().replace(",", "").replace("원", ""));

                //카테고리
                WebElement categoryElement = driver.findElement(By.cssSelector("#sub_container > div:nth-child(1) > div > ul > li:nth-child(3) > a"));
                String category = categoryElement.getAttribute("href").split("=")[1];

                //브랜드
                WebElement brandElement = driver.findElement(By.className("b_name"));
                String brand = brandElement.getText();

                //성별
                WebElement genderElement = driver.findElement(By.cssSelector("#sub_container > div:nth-child(1) > div > ul > li:nth-child(2) > a"));
                String gender = genderElement.getText();

                //이미지 객체 탐색
                List<WebElement> element = driver.findElements(By.id("img_01"));

                //폴더 생성
                String folderPath = makeFolder();

                //UUID
                String uuid = UUID.randomUUID().toString();

                //ProductDTO 객체 생성
                ProductDTO productDTO = new ProductDTO();

                productDTO.setPno(pno);
                productDTO.setBrand(brand);
                productDTO.setCategory(category);
                productDTO.setPrice(price);
                productDTO.setGender(gender);
                productDTO.setSale(0L);
                productDTO.setName(titleElement.getText());
                //productDTO.setImageDTOList();

                System.out.println(productDTO.toString());


                //이미지 DTO 객체
                List<ProductImageDTO> productImageDTOList = new ArrayList<>();

                for (int j = 0; j < element.size() - 1; j++) {

                    //이미지 주소
                    String imgURL = element.get(j).getAttribute("data-zoom-image");
                    //저장할 파일 이름 중간에 "_" 이용해서 구분
                    String saveName = "C:\\upload" + File.separator + folderPath + File.separator + uuid + "_" + j + "_" + pno + ".jpg";

                    //이미지 저장
                    BufferedImage jpg = ImageIO.read(new URL(imgURL));
                    File file = new File(saveName);
                    ImageIO.write(jpg, "jpg", file);

                    productImageDTOList.add(new ProductImageDTO(Long.parseLong("" + j), uuid, pno, folderPath));
                }

                //사이즈 DTO 객체
                List<ProductSizeDTO> productSizeDTOList = new ArrayList<>();

                int index = (gender.equals("WOMEN")) ? 9 : 13;
                Long firstSize = (gender.equals("WOMEN")) ? 220L : 240L;

                for (int k = 0; k < index; k++) {
                    Random random = new Random();
                    String result = "" + random.nextInt(200);
                    Long amount = Long.parseLong(result);
                    productSizeDTOList.add(new ProductSizeDTO(amount, firstSize));

                    firstSize += 5L;
                }
                productDTO.setImageDTOList(productImageDTOList);
                productDTO.setProductSizeDTOList(productSizeDTOList);

                productService.register(productDTO);

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            } finally {
                driver.quit();
            }
        }
    }

    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("//", File.separator);

        //make folder
        File uploadPathFolder = new File("C:\\upload", folderPath);

        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }
}