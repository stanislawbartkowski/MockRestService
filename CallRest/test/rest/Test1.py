'''
Created on 27 lut 2019

@author: sbartkowski
'''
import unittest
from com.rest import Rest

SERVERHOST="thinkde:8080"
APPNAME="RestMockServer"


class Test(unittest.TestCase):


    def setUp(self):
        self.R = Rest.RestApi(SERVERHOST,APPNAME) 

    def testName(self):
        res = self.R.postContent("Hello")
        print(res)
        
    def testFile(self):
        res = self.R.uploadFile("f.txt")
        print(res)

    def testFileBig(self):
        res = self.R.uploadFile("upgrade-error.txt")
        print(res)
        
    def testReset(self):
        self.R.resetCounter()
        
    def testgetCounter(self):
        val = self.R.getCounter()
        print(val)
        
    def testCounter(self):
        self.R.resetCounter()
        self.R.postContent("Hello")
        self.R.postContent("Hello")
        self.R.postContent("Hello")
        val = self.R.getCounter()
        print(val)
        self.assertEqual(val, "3", "Three expected after reset")

