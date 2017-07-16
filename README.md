# react-native-quicklook [![Build](https://img.shields.io/badge/build-passing-brightgreen.svg?style=flat)](#)[![Platform](https://img.shields.io/badge/platform-ios_android-brightgreen.svg?style=flat)](#)[![npm](https://img.shields.io/npm/v/react-native-quicklook.svg)](https://www.npmjs.com/package/react-native-quicklook)

This package helps us to view txt, pdf and office files in our application. In the iOS platform like mac system `Quick Look` function, but android, can only use another application to open.

## Content
* [Installation](#1)
* [Example](#2)
* [Usage](#3)
* [Author](#4)
* [License](#5)


## <a id=1>Installation</a>

In the project folder, enter the following commands

~~~javascript
// install
npm install react-native-quicklook
// link
react-native link react-native-quicklook
~~~

## <a id=2>Example</a>

* android

![](https://ws1.sinaimg.cn/mw690/962a6dfegy1fhluyzv4xzg20b40ha4qp.gif)

* iOS

![](https://ws1.sinaimg.cn/mw690/962a6dfegy1fhluz6mtwkg20b40k3kjl.gif)

## <a id=3>Usage</a>

~~~js
import QuickLookView from 'react-native-quicklook';

...
render() {
    return <QuickLookView fileUrl={"a.doc"} style={{flex:1}}/>
}
...
~~~

## <a id=4>Author</a>

Ruwei Li, liruwei0109@outlook.com

## <a id=5>License</a>

react-native-quicklook is available under the ISC license. See the LICENSE file for more info.





