import json
from selenium.webdriver.chrome.options import Options
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import requests
import urllib.request
from bs4 import BeautifulSoup

import time
import openpyxl
from openpyxl import Workbook
import datetime
import numpy as np
import pandas as pd

def get_driver():
    chrome_options = webdriver.ChromeOptions()
    driver = webdriver.Chrome(
        service=Service(ChromeDriverManager().install()), options=chrome_options
    )
    return driver


driver = get_driver()
driver.get('https://m.bunjang.co.kr/')
# page_data = driver.page_source


# 엑셀에 저장
wb = openpyxl.Workbook()
# wb = openpyxl.load_workbook("번개장터_태블릿_이어폰(버즈).xlsx")
sheet = wb.active

category = ["노트북", "태블릿", "스마트폰", "이어폰", "스마트워치"]
# 검색어 리스트 ( 노트북, 태블릿, 스마트폰, 스마트워치, 이어폰 순)
keywordList = [ [ 
    "갤럭시 북 i3 256", "갤럭시 북 i5", "갤럭시 북 i7", 
    "맥북 에어 2020 256", "맥북 에어 2020 512", "맥북 에어 2018 256", "맥북 에어 2018 512",  
    "맥북 프로 2021 256", "맥북 프로 2021 512", "맥북 프로 2020 256", "맥북 프로 2020 512", 
    "맥북 프로 2019 1tb", "맥북 프로 2019 256", "맥북 프로 2019 512", 
    "맥북 프로 2018 256", "맥북 프로 2018 512" 
    ], [
        "갤럭시 탭 s8 128",
        "갤럭시 탭 s8 256",
        "갤럭시 탭 s8 플러스 128"
        "갤럭시 탭 s8 플러스 256",
        "갤럭시 탭 s8 ultra 128",
        "갤럭시 탭 s8 ultra 256",
        "갤럭시 탭 s8 ultra 512",
        "갤럭시 탭 s7 128",
        "갤럭시 탭 s7 256",
        "갤럭시 탭 s7 fe 64",
        "갤럭시 탭 s7 fe 128",
        "갤럭시 탭 s7 플러스 128",
        "갤럭시 탭 s7 플러스 256",
        "갤럭시 탭 s7 플러스 512",
        "갤럭시 탭 s6 라이트 64",
        "갤럭시 탭 s6 라이트 128",
        "갤럭시 탭 s6 128",
        "갤럭시 탭 s6 256",
        "갤럭시 탭 s5 64",
        "갤럭시 탭 s5 128",
        "에어5 64",
        "에어5 256",
        "아이패드9 64",
        "아이패드 9 256",
        "아이패드 미니6 64",
        "아이패드 미니6 256",
        "아이패드 프로 11 128", 
        "아이패드 프로 11 256",
        "아이패드 프로 11 512", 
        "아이패드 프로 11 1tb",
        "아이패드 프로 11 2tb",
        "아이패드 프로 12.9 128",
        "아이패드 프로 12.9 256",
        "아이패드 프로 12.9 512", 
        "아이패드 프로 12.9 1tb",
        "아이패드 프로 12.9 2tb",
        "아이패드8 128",
        "아이패드8 32",
        "아이패드 에어4 64", 
        "아이패드 에어4 256",
        "아이패드7 32", 
        "아이패드7 128",
        "아이패드 에어3 64", 
        "아이패드 에어3 256",
        "아이패드 미니5 64", 
        "아이패드 미니5 256",
        "아이패드6 32",
        "아이패드6 128"
    ], [
        "갤럭시 s10 128", "갤럭시 s10 512", "갤럭시 s10 5g 512", "갤럭시 s10 5g 256", "갤럭시 s10 플러스 128", "갤럭시 s10 플러스 512",
        "s20 128", "s20 fe 128", "s20 울트라 256", " s20 울트라 512", "s20 플러스 256", "s20 bts 256", 
        "s21 256", "s21 울트라 256", "s21 플러스 256", "s22 256", "s22 울트라 256", "s22 울트라 1tb", "s22 플러스 256", 
        "s8 64", "s8 플러스 64", "s8 플러스 128", "s9 64", "s9 플러스 256", 
        "z폴드2 256", "z폴드3 256", "z폴드3 512", "z 플립 256", "z 플립3 256", 
        "노트20 256", "노트20 울트라 256", "노트8 64", "노트8 256", "노트9 128", "노트9 512", 
        "아이폰11 64", "아이폰11 128", "아이폰11 256", "아이폰11  프로 64", "아이폰11 프로 256", "아이폰11 프로 512", "아이폰11 프로 맥스 64", "아이폰11 프로 맥스 256", "아이폰11 프로 맥스 512", 
        "아이폰12 64", "아이폰12 128", "아이폰12 256", "아이폰12  프로 128", "아이폰12 프로 256", "아이폰12 프로 512", "아이폰12 프로 맥스 128", "아이폰12 프로 맥스 256", "아이폰12 프로 맥스 512", "아이폰12 미니 64", "아이폰12 미니 128", "아이폰12 미니 256", 
        "아이폰13 128", "아이폰13 256", "아이폰13 512", "아이폰13 프로 128", "아이폰13 프로 256", "아이폰13 프로 512", "아이폰13 프로 맥스 128", "아이폰13 프로 맥스 256", "아이폰13 프로 맥스 512", "아이폰13 미니 128", "아이폰13 미니 256", "아이폰13 미니 512",  
        "아이폰8 64", "아이폰8 128", "아이폰8 256", "아이폰8 플러스 64", "아이폰8 플러스 128", "아이폰8 플러스 256", 
        "아이폰se 16", "아이폰se 32", "아이폰se 64", "아이폰se 2020 64", "아이폰se 2020 128", "아이폰se 2020 256", "아이폰se 2022 64", "아이폰se 2022 128", "아이폰se 2022 256", 
        "아이폰 x 64", "아이폰 x 256", "아이폰 XR 64", "아이폰 XR 128", "아이폰 XR 256", "아이폰 XS 64", "아이폰 XS 256", "아이폰 XS 512"
    ], [
        "갤럭시 버즈 라이브", "갤럭시 버즈 프로", "갤럭시 버즈 플러스", "갤럭시 버즈2", "갤럭시 버즈", 
        "에어팟3", "에어팟프로", "에어팟 1세대", "에어팟 2세대", "에어팟 맥스", 
        "기어 서클", "기어 아이콘X", "기어 아이콘X(2018)", 
        "studio3 wireless", "solo3 wireless", 
        "t1", "t2", "t3", "t5", "t7", "t16", "q29 pro", "qc35",
        "e8", "wh-1000xm3", "wh-1000xm4", "wf-1000xm3", "wf-1000xm4" 
    ], [
        "갤럭시 워치", "갤럭시 워치 액티브2", "갤럭시 워치3", 
        "갤럭시 워치4", "갤럭시 워치4 클래식", "기어s2", "기어s3 프론티어", 
        "미워치 라이트", "애플워치3", "애플워치5", "애플워치6", "애플워치7", "애플워치SE", 
        "갤럭시 기어 S2 클래식", "갤럭시 기어 S2 스포츠", "갤럭시 기어 S3 클래식", 
        "기어 스포츠", "기어 핏", "기어 핏2", "기어핏2 프로", "갤럭시 핏", "갤럭시 핏e", 
        "애플워치 Series1", "애플워치2", "애플워치4", "미워치" 
    ] ]

# 제목 내에 포함되면 제외할 리스트
exceptList = ['삽니다', '스트랩', '밴드', '케이스', '충전', '구매']

# 유사도 분석위해 제목 저장할 리스트
pageData = []
itemNameList = []

# 몇 페이지까지 크롤링 할지 (total->검색결과)
total_page = 300
total_next = total_page // 10
last_page = total_page - total_next * 10

for a in keywordList:
    for b in a:

        # 키워드 입력
        # print(keywordList[1][46])
        keywordInput = driver.find_element(By.CSS_SELECTOR, 'input.sc-hMqMXs.cLfdog')
        keywordInput.click()
        keywordInput.send_keys(keywordList[3][1])
        keywordInput.send_keys(Keys.ENTER)
        time.sleep(1)

        recent = driver.find_element(By.XPATH, '/html/body/div[1]/div/div/div[4]/div/div[3]/div/div[2]/a[2]')
        recent.click()

        page = driver.find_element(By.XPATH, '/html/body/div[1]/div/div/div[4]/div/div[5]/div/a[8]')
        # /html/body/div[1]/div/div/div[4]/div/div[5]/div/a[{3}] -> 2
        page.click()
        # page = 3

        # 다음누르기 반복 몇번할건지 
        for c in range ( total_next ):
            page = 8
            print("c", c)

            # 이 페이지에 있는 쪽수만큼 반복
            # for d in range ( len(driver.find_elements(By.CSS_SELECTOR,'a.sc-kNBZmU.gdKIuz')) ):
            for d in range ( 10 ):
                print("d", d)

                # 한페이지 게시물 반복
                # for i in range(1, len(driver.find_elements(By.CSS_SELECTOR,'div.sc-ejGVNB.jrCaJ'))+1 ):
                for i in range(1, 101):    
                    time.sleep(1)

                    # 광고 제거 (광고글이면 넘어감)
                    itemAD = driver.find_element(By.XPATH, f'/html/body/div[1]/div/div/div[4]/div/div[4]/div/div[{i}]/a/div[2]/div[2]/div[2]/span').text
                    if (itemAD == 'AD'):
                        continue
                    else: itemDate = itemAD # 광고 아니면 날짜에 저장
                            
                    # 완료 태그 (완료면 저장하고 들어감)
                    try:
                        transtate = driver.find_element(By.XPATH, f'/html/body/div[1]/div/div/div[4]/div/div[4]/div/div[{i}]/a/div[1]')
                        transtate= transtate.text
                        # 사진에 거래완료 태그 있는지
                        if '완료' in transtate:
                            itemTranstate = '거래완료'
                        else:
                            itemTranstate = ''   
                    except:
                        itemTranstate = ''

                    # 제목 (제외항목 있으면 넘어감)
                    try:
                        itemName = driver.find_element(By.XPATH, f'/html/body/div[1]/div/div/div[4]/div/div[4]/div/div[{i}]/a/div[2]/div[1]').text
                        print(itemName)

                        for e in exceptList:
                            if(i in itemName):
                                exceptItem = True
                        
                        if (exceptItem == True):
                            continue

                        itemNameList.append(itemName)
                    except: itemName = None
                    # /html/body/div[1]/div/div/div[4]/div/div[4]/div/div[6]/a/div[2]/div[1]

                    # url
                    try:
                        itemUrl = driver.find_element(By.XPATH, f'/html/body/div[1]/div/div/div[4]/div/div[4]/div/div[{i}]/a')
                        itemUrl = itemUrl.get_attribute('href')
                    except:
                        itemUrl = None

                    # 가격 
                    try:
                        itemPrice = driver.find_element(By.XPATH, f'/html/body/div[1]/div/div/div[4]/div/div[4]/div/div[{i}]/a/div[2]/div[2]/div[1]').text
                        itemPrice = itemPrice.replace(',', '')
                    except:
                        itemPrice = None

                    # 게시글 들어가기
                    articles = driver.find_element(By.XPATH, f'/html/body/div[1]/div/div/div[4]/div/div[4]/div/div[{i}]') 
                    articles.click()
                    time.sleep(1)

                    # 상태
                    try:
                        itemState = driver.find_element(By.XPATH, '/html/body/div[1]/div/div/div[4]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]').text
                        if itemState == '새상품' : itemState = '상'
                        else : itemState = '중, 하'
                    except:
                        itemState = None
                    
                    # 카테고리
                    itemCategory = category[3]

                    # 검색어
                    itemKeyword = keywordList[3][1]

                    # 사이트
                    itemSource = "번개장터"
                    print(i, itemName, itemDate, itemPrice, itemState, itemTranstate, itemSource, itemUrl)

                    # 엑셀에 작성
                    # 제목, 날짜, 가격, 상태, 거래완료여부, 사이트, url
                    sheet.append(
                    [   
                        itemName,
                        itemDate,
                        itemTranstate,
                        itemPrice,
                        itemState,
                        itemSource,
                        itemUrl,
                        itemCategory,
                        itemKeyword
                    ]
                    )
                    wb.save(f"번개장터_태블릿_이어폰(버즈).xlsx")

                    # 이전으로 
                    driver.back()
                    time.sleep(1)
                
                page += 1

                # 만약 페이지번호가 12보다 크거나 같으면 (현재 10쪽이면) 다음버튼 누르기
                if(page >= 12):
                    break

                # 한페이지 반복 끝나면 다음 숫자 버튼 누르기
                # 1-> //*[@id="root"]/div/div/div[4]/div/div[5]/div/a[2]
                # 10-> //*[@id="root"]/div/div/div[4]/div/div[5]/div/a[11]
                # 다음-> //*[@id="root"]/div/div/div[4]/div/div[5]/div/a[12]
                # 11-> //*[@id="root"]/div/div/div[4]/div/div[5]/div/a[2]
                pages = driver.find_element(By.XPATH, f'//*[@id="root"]/div/div/div[4]/div/div[5]/div/a[{page}]')
                pages.click()
            
            # 다음버튼(>) 클릭
            try:
                pages = driver.find_element(By.XPATH, f'//*[@id="root"]/div/div/div[4]/div/div[5]/div/a[{page}]')
                pages.click()
            except: break

        # 검색창 지우기
        keywordInput.clear()


# wb.save(f"번개장터_태블릿.xlsx")
driver.close()









