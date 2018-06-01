#-*- coding: utf-8 -*-
from selenium import webdriver

import os
import time
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options

class Sel():
    def setUp(self, url, search):
        self.threadCount = 0                # 쓰레드 개수
        chrome_options = Options()
        chrome_options.add_argument("--headless")
        chrome_options.add_argument('no-sandbox')
        self.myDriver  = webdriver.Chrome('C:\dev\python\myvenv\Scripts\chromedriver.exe')
        self.myDriver.implicitly_wait(1)    # 웹자원 로드를 위해 1초 기다려준다.

        self.verificationErrors = []        #
        self.accept_next_alert = True       #
        self.count = 0                      # 모지..
        naverDriver = self.myDriver  # 생성된 웹드라이버 ---> 페이스북 드라이버 정의

        self.search = search
        naverDriver.get(url)     # url 입력

        print("\n")
        print("#################################################")
        print(" [네이버 크롤링 START] #############################")
        print("#################################################")
        print("\n")

        self.Search(naverDriver)
        pkNum= 1

        savedata =""
        self.NaverCrawling(naverDriver, savedata,pkNum)
        #self.FacebookCrawling(naverDriver, savedata, countStart)

        print("#################################################")
        print(" [네이버 크롤링 END] #############################")
        print("#################################################")


    def Search(self, naverDriver):
        print("[########## ["+ self.search +"] 검색 시작 ##########]")
        ## 검색창 클릭 && 검색어입력 + [ENTER] ##
        tmp = naverDriver.find_element_by_xpath('//*[@id="PM_ID_ct"]/div[1]/div[2]/div[1]/ul[1]/li[1]/a/span[2]').text
        #print("tmp="+tmp)

        naverDriver.find_element_by_xpath('//*[@id="query"]').clear()
        try:
            naverDriver.find_element_by_class_name('green_window').click()
            time.sleep(0.2)
            tmp = naverDriver.find_element_by_xpath('//*[@id="search_btn"]/span[1]').text

            naverDriver.find_element_by_xpath('//*[@id="query"]').send_keys(self.search)
            naverDriver.find_element_by_xpath('//*[@id="search_btn"]').click()

            time.sleep(1)
            print("### ["+ self.search +"] 성공 ###")
        except:
            print("### ["+ self.search +"] 오류!! ###")
            pass


        ## 검색창 클릭 && 검색어입력 + [ENTER] ##


        try:
            naverDriver.find_element_by_css_selector('.lnb4 > a').click()
            time.sleep(0.3)
            print("### [뉴스] 클릭 ###")
        except:
            print("### [뉴스] 오류!! ###")
            pass

        try:
            naverDriver.find_element_by_css_selector('#_search_option_btn').click()
            print("### [검색옵션] 클릭 ###")

        except:
            print("### [검색옵션] 오류!! ###")
            pass


        try:
            naverDriver.find_element_by_css_selector('#snb > div > ul > li:nth-child(4) > a').click()
            print("### [기간] 클릭 ###")

        except:
            print("### [기간] 오류!! ###")
            pass


        try:
            naverDriver.find_element_by_css_selector('#_nx_option_date > div.group_choice > ul:nth-child(1) > li:nth-child(2) > a').click()
            print("### [1일] 클릭 ###")
            time.sleep(1)

        except:
            print("### [1일] 오류!! ###")
            pass


    def NaverCrawling(self, naverDriver, savedata, pkNum):
        print("[########## [네이버 크롤링] 시작 ##########]")
        naverNews =  naverDriver.find_elements_by_css_selector(".type01 > li > dl > dd > ._sp_each_url")

        #print("naverNews.length="+str(len(naverNews)))

        for i in range(0, len(naverNews)):
            try:
                naverNews[i].click()
                time.sleep(0.3)
                print("### ["+str(i)+"th 네이버뉴스] 클릭 ###")

                naverDriver.switch_to_window(naverDriver.window_handles[1])

                try:
                    print("### [전체 댓글] 클릭!! ###")
                    naverDriver.find_element_by_css_selector('#cbox_module > div > div.u_cbox_view_comment > a').click()
                    time.sleep(0.3)
                except:
                    print("### [전체 댓글] 오류!! ###")


                while True:
                    print("11111111111")
                    if naverDriver.find_element_by_css_selector('#cbox_module > div > div.u_cbox_paginate > a').text == "":
                        print("###====================== [더보기] 없음!! ==========================###")
                        break
                    else:
                        print("222222222222222")
                        try:
                            naverDriver.find_element_by_css_selector('#cbox_module > div > div.u_cbox_paginate > a').click()
                            time.sleep(0.3)
                            print("###====================== [더보기] 클릭!! ==========================###")
                        except:
                            print("### [더보기] 클릭 오류!! ###")

                try:
                    writers = naverDriver.find_elements_by_css_selector('.u_cbox_nick_area > .u_cbox_nick')
                    comments = naverDriver.find_elements_by_css_selector('.u_cbox_text_wrap > .u_cbox_contents')
                    print("[### [ 네이버댓글 -크롤링] 시작 ###]")
                    for j in range(0, len(writers)):

                        num = "man00"+str(pkNum)

                        savedata += "<__num__>"+num +"\n"
                        savedata += "<__writer__>"+writers[j].text +"\n"
                        savedata += "<__comment__>"+comments[j].text +"\n"


                        savedata += "\n\n"
                        print(savedata)

                        fileName = "Manchester_United.fgf"
                        savePath = "ManCrawling"
                        if not os.path.exists(savePath):
                            os.makedirs(savePath)

                        with open(os.path.join(savePath,fileName),'wb') as file:
                            file.write(bytes(savedata, encoding="utf-8", errors="ignore"))

                        pkNum += 1

                    print("==================================")
                    time.sleep(0.3)
                except:
                    print("[댓글] 오류")


                naverDriver.close()
                naverDriver.switch_to_window(naverDriver.window_handles[0])


            except:
                print("### [네이버뉴스] 클릭 오류!! ###")
                #naverDriver.find_element_by_css_selector('#main_pack > div.news.mynews.section > div > div.news_guide_bx > a.txt_guide').click()
                pass



        try:
            naverDriver.find_element_by_css_selector('#main_pack > div.paging > a.next').click()
            time.sleep(0.3)
            print("### [다음페이지] 클릭 ###")
            self.NaverCrawling(naverDriver, savedata,pkNum )
        except:
            print("### [다음페이지-크롤링] END ###")
            print("\n")
            pass



def start():
    search = "레알마드리드"
    sel = Sel()

    sel.setUp("https://www.naver.com", search)

if __name__ == "__main__":
    start()
