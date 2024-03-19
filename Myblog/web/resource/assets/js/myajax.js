function Ajax(method,url,data,progressData) {
    this.method = method;
    this.url = url;
    this.data = data;
    this.progressData = progressData;
    //组件
    this.xmlHttpRequest = function () {
        if (window.ActiveXObject){
            return new ActiveXObject("Microsoft.XMLHTTP");
        }else if (window.XMLHttpRequest){
            return new XMLHttpRequest();
        }
    }()
    //发送Ajax请求
    this.request = function () {
        //指定回调函数
        this.xmlHttpRequest.onreadystatechange = this.callback;
        this.xmlHttpRequest.open(this.method,this.url,true);
        if (this.method.toUpperCase() === "POST"){
            //设置请求头
            this.xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            this.xmlHttpRequest.send(this.data);
        }
        else
            this.xmlHttpRequest.send(null);
    }

    //回调函数
    this.callback = function () {
        if (this.readyState === 4){
            //分析响应头内容
            let data = null;
            let contentType = this.getResponseHeader("Content-Type");
            if (contentType.indexOf("xml") !== -1){
                data = this.responseXML;
            }else if (contentType.indexOf("json")){
                data = this.responseText;
                data = JSON.parse(data);
            }else {
                data = this.responseText;
            }
            progressData(data);
        }
    }
}