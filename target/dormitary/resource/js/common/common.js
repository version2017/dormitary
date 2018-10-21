var common = {
	addLoadEvent : function(func){
		var oldonload = window.onload;
		if(typeof window.onload != 'function'){
			window.onload = func;
		}else{
			window.onload = function(){
				oldonload();
				func();
			}
		}
	},
	isEmpty : function(val){
		if((val==undefined) || (val=="") || (val==null))
			return true;
		else
			return false;
   }, 
	isCookieContainVar : function(variable){
		var value = $.cookie(variable);
		return common.isEmpty(value);
    },
    viewType : function(o){
    //查看数据类型
	   	var gettype=Object.prototype.toString;
	    alert(gettype.call(o));
    },
    viewObject : function(o){
    //查看对象内容(属性和值)
    	for(i in o){
    		alert("paramName:"+i+" value:"+o[i]);
    	}
    },
    //正则校验字符串长度(结果不对,暂不使用)
//  regCheck : function(str, minLen, maxLen){
//  	var reg = new RegExp("\w{" + minLen + "," + maxLen + "}");
//  	alert(reg);
//      if(!reg.test(name)){
//          return false;
//      }
//      return true;
//  },
	//从地址栏获取参数
	getParamsOfUrl : function(paramName){
	    var vars = [], hash;
	    var q = document.URL.split('?')[1];
	    if(q != undefined){
	        q = q.split('&');
	        for(var i = 0; i < q.length; i++){
	            hash = q[i].split('=');
	            vars.push(hash[1]);
	            vars[hash[0]] = hash[1];
	        }
	    }
	    return vars[paramName];
	}
};

//添加页面初始化事件
common.addLoadEvent(common.init);

var gettype=Object.prototype.toString;
var typeJudge = {
	isObj:function(o){
    	return gettype.call(o)=="[object Object]";
	},
	isArray:function(o){
    	return gettype.call(o)=="[object Array]";
	},
	isNULL:function(o){
		return gettype.call(o)=="[object Null]";
	},
	isDocument:function(){
		return gettype.call(o)=="[object Document]"||"[object HTMLDocument]";
	},
	isBoolean:function(){
		return gettype.call(o)=="[object Boolean]";
	},
	isUndefined:function(){
		return gettype.call(o)=="[object Undefined]";
	},
	isString:function(){
		return gettype.call(o)=="[object String]";
	},
	isNumber:function(){
		return gettype.call(o)=="[object Number]";
	},
	isFunction:function(){
		return gettype.call(o)=="[object Function]";
	}
};
