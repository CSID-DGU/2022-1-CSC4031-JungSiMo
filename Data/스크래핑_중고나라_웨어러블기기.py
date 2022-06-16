from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager


import requests
from bs4 import BeautifulSoup

import time
import pyperclip
import pymongo

import openpyxl
from openpyxl import Workbook
import datetime
import numpy as np
import pandas as pd


def set_chrome_driver():
    chrome_options = webdriver.ChromeOptions()
    browser = webdriver.Chrome(
        service=Service(ChromeDriverManager().install()), options=chrome_options
    )
    return browser


browser = set_chrome_driver()
browser.maximize_window()

url = "https://cafe.naver.com/joonggonara.cafe?iframe_url=/ArticleList.nhn%3Fsearch.clubid=10050146%26search.boardtype=L%26viewType=pc"
browser.get(url)
# time.sleep(1)

# 로그인 페이지 이동
loginBtn = browser.find_element(By.ID, "gnb_login_button")
loginBtn.click()
# time.sleep(1)

# 일회용 번호 클릭
loginOneBtn = browser.find_element(By.ID, "ones")
loginOneBtn.click()

oneNum = str("00915932")
inputNum = browser.find_element(By.ID, "disposable")
inputNum.click()
inputNum.send_keys(oneNum)
loginBtn = browser.find_element(By.ID, "otnlog.login")
loginBtn.click()

baseUrl = "https://cafe.naver.com/joonggonara"
junggonaraId = 10050146
# 카테고리 ID
mobilePhoneId = 339  # 휴대폰
tabletId = 749  # 태블릿
sideDeviceId = 427  # 주변기기/악세사리
laptopId = 334  # 노트북
menu = ["휴대폰", "태블릿", "주변기기/악세사리", "노트북"]
menuIds = [mobilePhoneId, tabletId, sideDeviceId, laptopId]
# 필요한 카테고리 열기
categoryListUrl = f"{baseUrl}?iframe_url=/ArticleList.nhn%3Fsearch.clubid={junggonaraId}%26search.menuid={menuIds[2]}%26search.boardtype=L"
browser.get(categoryListUrl)

# 엑셀 작성
# 새로운 키워드를 검색할때마다 기존의 파일 .xlsx 밑에 이어서 데이터가 저장되도록
# wb = openpyxl.Workbook()
# sheet = wb.active
category = ["휴대폰", "태블릿", "웨어러블 기기", "노트북"]
wb = openpyxl.load_workbook("중고나라_웨어러블 기기_정윤(이어폰).xlsx")
sheet = wb.active
# sheet.append(["사이트", "카테고리", "제목", "가격", "상품 상태", "작성 날짜", "상품 설명", "url", "거래 상태"])

time.sleep(1)
browser.switch_to.frame("cafe_main")
# 검색어 설정 - 제목만으로
# keywordSelect = browser.find_element(By.ID, "currentSearchBy")
# titleSelect = browser.find_element(By.XPATH, '//*[@id="divSearchBy"]/ul/li[2]/a')
# titleSelect.click()

# 검색어 입력
keywordList = [
    "기어 s2",
    "기어 s2 클래식",
    "기어 s2 스포츠",
    "기어 s3 프론티어",
    "기어 s3 클래식",
    "기어 스포츠",
    "갤럭시 워치1",
    "갤럭시 워치3",
    "갤럭시 워치4",
    "갤럭시 워치4 클래식",
    "갤럭시 워치 액티브",
    "기어 서클",
    "기어 아이콘",
    "기어 아이콘 2018",
    "버즈",
    "버즈 플러스",
    "버즈 라이브",
    "버즈 프로",
    "버즈2"
]

keywordInput = browser.find_element(By.NAME, "query")
keywordInput.click()
keywordInput.send_keys(keywordList[14])
keywordInput.send_keys(Keys.ENTER)

# iframe 안의 내용으로
# browser.switch_to.frame("cafe_main")

# 50개씩 보기로 변경
listSizeSelect = browser.find_element(By.ID, "listSizeSelectDiv")
listSizeSelect.click()
fiftySize = browser.find_element(By.XPATH, '//*[@id="listSizeSelectDiv"]/ul/li[7]/a')
fiftySize.send_keys(Keys.ENTER)

# 총 몇 페이지 자료를 모을지
total_page = 100
# 페이지 개수 나누기
total_next = total_page // 10
last_page = total_page - total_next * 10


# articles = browser.find_elements(By.CSS_SELECTOR, "a.article")
# 게시물 들어가고 -> 나오고 -> 들어가고 ... -> 나왔을 때, 현재 페이지의 마지막 게시물인 경우 다음 페이지 클릭
# 10 페이지 마지막 게시물인 경우 -> 화살표 클릭

# 검색어 리스트 내 첫번째 게시물 클릭
# articles[0].click()
# time.sleep(5)

incre = 0

pages = browser.find_element( By.XPATH,f'//*[@id="main-area"]/div[7]/a[{9}]' )
pages.click()
# pages = browser.find_element( By.XPATH,f'//*[@id="main-area"]/div[7]/a[{11}]' )
# pages.click()

for a in range(total_next):
    # 0 ~ 10 번이면 page = 1부터 ~ 10까지 / 다음 = 11
    # 11 ~ 20 번이면 page = 2부터 ~ 11까지 / 다음 = 12
    # 다음이 없는 경우, page = 2부터 ~ 11까지

    # pages = browser.find_element(By.XPATH, f'//*[@id="main-area"]/div[7]/a[{15}]')
    # pages.click()

    if a == 0:
        page = 1
    elif a > 0 and a != total_next - 1:
        page = 2
    elif a == total_next - 1:
        page = 2

    for b in range(10):
        page = 9
        for i in range(len(browser.find_elements(By.CSS_SELECTOR, "a.article"))):

            # 게시글 들어가기
            articles = browser.find_elements(By.CSS_SELECTOR, "a.article")[i]
            articles.click()
            time.sleep(1)

            # 정보 추출
            try:
                date = browser.find_element(By.CSS_SELECTOR, ".date").text
            except:
                date = None

            try:
                title = browser.find_element(By.CSS_SELECTOR, "h3.title_text").text.strip(" ")
            except:
                title = None

            try:
                url = browser.find_element(By.CSS_SELECTOR, ".button_url").get_attribute("href")
            except:
                url = None

            print("date : ", date)
            print("title : ", title)
            print("url : ", url)

            try:
                # 거래 상태
                tradeStatus = browser.find_element(By.CSS_SELECTOR, ".ProductName").find_element(
                    By.TAG_NAME, "em"
                )
                # tradeStatus = soup.find_all(
                #     "em", attrs={"class": ["SaleLabel safety", "SaleLabel sold", "SaleLabel selling"]}
                # )
                if tradeStatus:
                    tradeStatus = tradeStatus.text
                else:
                    tradeStatus = None
            except:
                tradeStatus = None
                pass

            print("tradeStatus : ", tradeStatus)

            try:
                # 상품 가격
                priceStr = browser.find_element(By.CSS_SELECTOR, ".ProductPrice")
                if priceStr:
                    priceStr = priceStr.text
                    if priceStr:
                        priceNumStr = priceStr[:-1]
                        price = priceNumStr.replace(",", "")
                        price = int(price)
                        print("price : ", price)
                else:
                    price = ""

            except:
                price = None
                pass

            print("price : ", price)

            try:
                # 상품 상태
                status = browser.find_element(By.CSS_SELECTOR, ".detail_list")
                if status:
                    statusTitle = status.find_element(By.CSS_SELECTOR, ".list_title").text
                    if statusTitle == "상품 상태":
                        statusContent = status.find_element(By.CSS_SELECTOR, ".list_detail").text
                        print("status : ", status)
                        print("statusTitle : ", statusTitle)
                        print("statusContent : ", statusContent)
                    else:
                        statusContent = "사용감 있음"

                else:
                    statusContent = ""
            except:
                statusContent = None
                pass

            try:
                # 상품 설명
                explanation = browser.find_element(By.CSS_SELECTOR, ".se-main-container")
                if explanation:
                    explanation = explanation.text
                else:
                    explanation = ""
            except:
                explanation = None
                pass

            print("explanation : ", explanation)
           
            keyword = keywordList[14]

            # 엑셀에 작성
            # ws.append(["사이트", "카테고리", "제목", "가격", "상품 상태", "작성 날짜", "상품 설명", "url", "거래 상태"])
            sheet.append(
                [
                    "중고나라",
                    f"{category[2]}",
                    title,
                    price,
                    statusContent,
                    date,
                    explanation,
                    url,
                    tradeStatus,
                    keyword
                ]
            )
            wb.save(f"중고나라_{category[2]}_정윤(이어폰).xlsx")
            # 이전 페이지(목록 페이지)로
            browser.back()
            browser.switch_to.frame("cafe_main")

        # 한 페이지 내 50개 게시물 확인 완료 -> 다음 페이지로

        # pages = browser.find_element(By.CSS_SELECTOR, "prev-next a")[0 + 1 + 0]
        page += 1

        # 만약, page += 1 처리 후 page > 10 이면 -> 다음 페이지 번호 누르는 것이 아닌 다음 버튼 누르기
        if page > 10:
            break
        pages = browser.find_element(By.XPATH, f'//*[@id="main-area"]/div[7]/a[{page}]')
        pages.click()

    pages = browser.find_element(By.XPATH, f'//*[@id="main-area"]/div[7]/a[{page}]')
    pages.click()

# selenium 끝내고 엑셀 파일 저장
# wb.save(f"중고나라_{category[2]}_정윤(워치).xlsx")
