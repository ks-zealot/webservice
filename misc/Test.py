from xml.etree import ElementTree

from pip._vendor import requests

xml = """<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>new-agt</request-type>
 <login>1234567890</login>
 <password>password</password>
</request>
"""
myheaders = {
    "User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0",
    "Accept-Encoding": "gzip, deflate",
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language": "en-US,en;q=0.5",
    "Connection": "keep-alive",
    "Content-Type": "application/xml"
}


r =requests.post("http://localhost:8080/registerAbonent", headers = myheaders, data=xml)
print (r.text)#проверяем, не пропускает ли плохой пароль
root = ElementTree.fromstring(r.text)

assert (int(root.find("result-code").text) == 3), "Проверка на плохой пароль не прошла"
xml = """<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>new-agt</request-type>
 <login>1234567890</login>
 <password>ghl2g544DDF35345</password>
</request>
"""

r =requests.post("http://localhost:8080/registerAbonent", headers = myheaders, data=xml)
print (r.text)#проверяем, что все сохранилось правильно
root = ElementTree.fromstring(r.text)
assert (int(root.find("result-code").text) == 0), "Проверка на сохранение не прошла"

r =requests.post("http://localhost:8080/registerAbonent", headers = myheaders, data=xml)
print (r.text)#проверяем, resultcode 1 если повторно сохраняем того же абонента
root = ElementTree.fromstring(r.text)
assert (int(root.find("result-code").text) == 1), "Проверка на повторное сохранение не прошла"


xml = """<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>new-agt</request-type>
 <login>22</login>
 <password>ghl2g544DDF35345</password>
</request>
"""

r =requests.post("http://localhost:8080/registerAbonent", headers = myheaders, data=xml)
print (r.text)#проверяем, что не даст сохранить абонента с непрафильным форматом
root = ElementTree.fromstring(r.text)
assert (int(root.find("result-code").text) == 2), "Проверка на неправильный формат номера не прошла"



xml ="""<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>agt-bal</request-type>
 <login>1234567890</login>
 <password>ghl2g544DDF35345</password>
</request>
"""

r =requests.get("http://localhost:8080/getBalance", headers = myheaders, data=xml)
print (r.text)#проверяем, запрос баланаса

root = ElementTree.fromstring(r.text)
assert (float(root.find("bal").text) == 0.0), "Проверка запроса баланса"




xml ="""<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>agt-bal</request-type>
 <login>1234567899</login>
 <password>ghl2g544DDF35345</password>
</request>
"""

r =requests.get("http://localhost:8080/getBalance", headers = myheaders, data=xml)
print (r.text)#проверяем, что на несуществуещего абонента вернет ошибку
root = ElementTree.fromstring(r.text)
assert (int(root.find("result-code").text) == 11), "Проверка запроса баланса несуществующего абонента не прошла"

xml ="""<?xml version="1.0" encoding="utf-8"?>
<request>
 <request-type>agt-bal</request-type>
 <login>1234567890</login>
 <password>password</password>
</request>
"""

r =requests.get("http://localhost:8080/getBalance", headers = myheaders, data=xml)
print (r.text)#проверяем, что вернет ошибку принеправильном пароле
root = ElementTree.fromstring(r.text)
assert (int(root.find("result-code").text) == 12), "Проверка запроса баланса с неправильным паролем"

