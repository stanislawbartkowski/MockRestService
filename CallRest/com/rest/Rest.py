'''
Created on 27 lut 2019

@author: sbartkowski
'''

import requests
import os

class RestError(Exception): 
  
    # Constructor or Initializer 
    def __init__(self, err,errmess):
        self.err = err 
        self.errmess =  errmess
  
    # __str__ is to print() the value 
    def __str__(self): 
        return(self.err + " " + self.errmess) 

class RestApi :

    def __init__(self,host):
        self.SERVERHOST = host

    def __getRestURL(self,rest="rest"):
        if rest : return "http://"+ self.SERVERHOST + "/rest"
        return "http://"+ self.SERVERHOST + "/"

    def __getText(self,r):
        if r.status_code == 204 : return ""
        if r.status_code != 200 : raise(RestError("Error while reading REST data",str(r.content)))
        return r.text

    def __getRest(self,url):
        r = requests.get(url)
        return self.__getText(r)

    def __getRequest(self,rest):
        url = self.__getRestURL() + "/" + rest
        return self.__getRest(url)



    def __postContent(self,rest,content,cookies = None):
        url = self.__getRestURL() + "/" + rest + "?content=" + content
        if cookies : r = requests.post(url,cookies=cookies)
        else : r = requests.post(url)
        return self.__getText(r)

    def postContent(self,content,cookies=None):
        return self.__postContent("postform", content,cookies)

    def uploadFile(self,filename) :
        c = os.path.dirname(os.path.abspath(__file__))
        fname = os.path.join(c,"../../resource/",filename)
        files = { 'file' : open(fname,'r')}
        url = self.__getRestURL(None) + "/upload"
        r = requests.post(url,files=files)
        return self.__getText(r)

    def resetCounter(self):
        self.__getRequest("resetcounter")
    
    def getCounter(self):
        return self.__getRequest("counter")    
