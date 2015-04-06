# tinyurl4j
- A library to get tinyurl by calling [TinyurlWrapper](https://github.com/XinyueZ/tinyurl-wrapper) for Android.
- [Download](https://github.com/XinyueZ/tinyurl4j/tree/master/tinyurl4j/release)
- Sample:
[![https://play.google.com/store/apps/details?id=com.tinyurl4j.sample](https://dl.dropbox.com/s/phrg0387osr3riz/images.jpeg)](https://play.google.com/store/apps/details?id=com.tinyurl4j.sample)

# Latest Update:

Use [retrofit](https://github.com/square/retrofit).

Depreated old ```Api.Call```.

Use ```Api.getTinyUrl```instead ```Api.Call```

Code
============

```java
		Api.getTinyUrl("http://g.cn", new   Callback<Response>() {
			@Override  
			public void success(Response response,  retrofit.client.Response response2) {
	         //.... ie. response.getResult();
            }

			public void failure(RetrofitError error) {
				//.....
			}
});

```

```java

            Copyright 2015 Xinyue Zhao

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

```
