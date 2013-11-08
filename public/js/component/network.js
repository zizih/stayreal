(function (win) {
  
  var iAjax = function () {
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
      this.xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
      this.xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
  
  iAjax.prototype.get = function (url, success, err){
    this.xmlhttp.open("GET", url, true);
    this.xmlhttp.send();
    console.log(this.xmlhttp);
  }
  
  iAjax.prototype.post = function (params, url, success, err){
    this.xmlhttp.open("POST", url, true);
    this.xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    this.xmlhttp.send(SON.stringify(params));
  }
  
  win.iAjax = iAjax;
  
})(window);
