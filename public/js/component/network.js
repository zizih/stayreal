(function (win) {
  
  var iAjax = function () {
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
      this.xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
      this.xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
  
  iAjax.prototype.get = function (options){
    this.url = options.url;
    this.success = options.success;
    this.err = options.err;
    this.xmlhttp.open("GET", this.url, true);
    this.xmlhttp.send();
    var that = this;
    this.xmlhttp.onreadystatechange = function () {
      if(that.xmlhttp.readyState == 4) {
        if(that.xmlhttp.status == 200 || that.xmlhttp.status == 202) {
          that.success && that.success(JSON.parse(that.xmlhttp.responseText));
        } else {
          that.err && that.err(that.xmlhttp.responseText);
        }        
      }
    }
  }
  
  iAjax.prototype.post = function (options){
    this.data = options.data;
    this.url = options.url;
    this.success = options.success;
    this.err = options.err;
    this.xmlhttp.open("POST", this.url, true);
    this.xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    this.xmlhttp.send(JSON.stringify(this.data));
    var that = this;
    this.xmlhttp.onreadystatechange = function () {
      if(that.xmlhttp.readyState == 4) {
        if(that.xmlhttp.status == 200 || that.xmlhttp.status == 202) {
          that.success && that.success(JSON.parse(that.xmlhttp.responseText));
        } else {
          that.err && that.err(that.xmlhttp.responseText);
        }        
      }
    }
  }
  
  win.iAjax = iAjax;
  
})(window);
