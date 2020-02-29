# MockRestService

A simple solution to mock network traffic as HTTP POST/GET request. It is also an example of utilizing simple RestService library. https://github.com/stanislawbartkowski/RestService

Can be easily extended to more HTTP methods.<br><br>
The solution as provided as two Intellij Idea projects: the server and the client. The server is standalone Java application, the only prerequisite is RestService library mentioned above.It does not require any web server container like Tomcat or Jetty.

The client is Python 3 script.

# Installation
> 
Download and install RestService to the local Maven repository.<br>
https://github.com/stanislawbartkowski/RestService

# Server
Get ready to use *war* file (https://github.com/stanislawbartkowski/MockRest/tree/master/RestMockServer/war) and deploy to Tomcat container.<p>
Server part is ready.

You can also download the whole Eclipse project and extend according to your needs.<br>

*Prerequisite for Eclipse project*<br>

The dependecy is resolved using *iy* Eclipse plugin. Install the plugin beforehand.

* POST request
> curl -X POST  http://localhost:8080/RestMockServer/rest/postform?content=Hello

* File upload
> curl -F 'data=@file.txt' http://localhost:8080/RestMockServer/upload

There is an internal counter scoring the number of POST requests received
* Reset counter
> curl -X GET  http://localhost:8080/RestMockServer/rest/resetcounter
* The current value of the counter
> curl -X GET  http://localhost:8080/RestMockServer/rest/counter

# Client
## Prerequisites
* Python 3 (tested with Python 3.6 level)<br>
> yum install python36<br>

* *requests* package
> yum install python36-pip<br>
> python36 -m pip install requests<br>

## Run unit test

Modify the hostname and the application name in Tomcat server.

> cd MockRest/CallRest/test/rest<br>
> vi Test1.py<br>
```
SERVERHOST="localhost:8080"
APPNAME="RestMockServer"
```
Run the test.<br>

> cd MockRest/CallRest/test/rest<br>
> PYTHONPATH=../..   python36 -m unittest Test1.py 
```
/home/user/rest/MockRest/CallRest/test/rest/Test1.py:18: ResourceWarning: unclosed file <_io.TextIOWrapper name='/home/user/rest/MockRest/CallRest/com/rest/../../resource/f.txt' mode='r' encoding='UTF-8'>
  res = R.uploadFile("f.txt")
<html><head></head><body>File f.txt uploaded successfully.<br><a href="UploadDownloadFileServlet?fileName=f.txt">Download f.txt</a></body></html>
./home/user/rest/MockRest/CallRest/test/rest/Test1.py:22: ResourceWarning: unclosed file <_io.TextIOWrapper name='/home/user/rest/MockRest/CallRest/com/rest/../../resource/upgrade-error.txt' mode='r' encoding='UTF-8'>
  res = R.uploadFile("upgrade-error.txt")
<html><head></head><body>File upgrade-error.txt uploaded successfully.<br><a href="UploadDownloadFileServlet?fileName=upgrade-error.txt">Download upgrade-error.txt</a></body></html>
.received
.
----------------------------------------------------------------------
Ran 3 tests in 0.021s

OK

```
## Run Rest/HTTP client
> cd MockRest/CallRest<br>
> PYTHONPATH=. python36 com/MainRun.py<br>

* run.sh Single run
* runattack.sh Launch a seriers of  *run.sh* in parallel
