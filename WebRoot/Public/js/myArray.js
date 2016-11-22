function checkRadio(n)
{
    var RadioGroupName;
	var q;
	var flag; 
    flag=false;
    RadioGroupName=document.getElementsByName(n);
    for(var j=0; j<RadioGroupName.length; j++) 
    {
        if(RadioGroupName[j].checked)
        { 
		    flag=true;
            break;
        }
    }
    if(flag==false)
    {
        return false;
    }else{
		return true;
	}
}
function getRadio(n)
{
	var RadioGroupName;
	var q;
    RadioGroupName=document.getElementsByName(n);
    for(var j=0; j<RadioGroupName.length; j++) 
    {
         if(RadioGroupName[j].checked)
         { 
			 q=RadioGroupName[j].value;
			 return q;
             break;
         }
    }
}
function checkCheckbox(n){
   var checkboxes=document.getElementsByName(n);
   var count = 0;
   for(var j=0;j<checkboxes.length;j++){
       if(checkboxes[j].checked==true) count ++;
   }
   return count;
}
//全角字符转半角字符
function DBC2SBC(str)
{
    var result="";
    for(var i=0;i<str.length;i++)
    {
        code = str.charCodeAt(i);
        if(code >= 65281 && code <= 65373){
            var d=str.charCodeAt(i)-65248;
            result += String.fromCharCode(d);
        }else if (code == 12288){
            var d=str.charCodeAt(i)-12288+32;
            result += String.fromCharCode(d);
        }else{
            result += str.charAt(i);
        }
    }
    return result;
}
//身份证验证
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
function IdCardValidate(idCard) {   
    idCard = trim(idCard.replace(/ /g, ""));   
    if (idCard.length == 15) {   
        return isValidityBrithBy15IdCard(idCard);   
    } else if (idCard.length == 18) {   
        var a_idCard = idCard.split("");// 得到身份证数组   
        if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   
            return true;   
        }else {   
            return false;   
        }   
    } else {   
        return false;   
    }   
}   

function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0; // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];// 加权求和   
    }   
    valCodePosition = sum % 11;// 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   

function maleOrFemalByIdCard(idCard){   
    idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。   
    if(idCard.length==15){   
        if(idCard.substring(14,15)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else if(idCard.length ==18){   
        if(idCard.substring(14,17)%2==0){   
            return 'female';   
        }else{   
            return 'male';   
        }   
    }else{   
        return null;   
    }   
}   

function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
 
function isValidityBrithBy15IdCard(idCard15){   
    var year =  idCard15.substring(6,8);   
    var month = idCard15.substring(8,10);   
    var day = idCard15.substring(10,12);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));    
    if(temp_date.getYear()!=parseFloat(year)   
        ||temp_date.getMonth()!=parseFloat(month)-1   
        ||temp_date.getDate()!=parseFloat(day)){   
        return false;   
    }else{   
        return true;   
    }   
}   
   
function trim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
} 


//电话号码验证
String.prototype.Trim = function() {  
  var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);  
  return (m == null) ? "" : m[1];  
}

String.prototype.isMobile = function() {  
  return (/^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(this.Trim()));  
} 

String.prototype.isTel = function()
{
    //"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
}

function chkForm(num) {  
     if(num.isMobile()||num.isTel())  {  
         num = num.Trim();  
         return true;  
     }else{  
         return false;        
     }          
}

function chkTel(num) {  
    if(num.isTel())  {  
        num = num.Trim();  
        return true;  
    }else{  
        return false;        
    }          
}

function chkMobile(num) {  
    if(num.isMobile())  {  
       num = num.Trim();  
       return true;  
    }else{  
       return false;        
    }          
}

function chkemail(email)
{
	var temp = email;
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(!myreg.test(temp)){
		return false;
	}else return true;
}


function chkzip(zipcode){

    num = zipcode.length;
	if(num == 0){
	 return false;
	}
    for(i=0;i<num;i++)
    {
        postcode=zipcode.charAt(i);

        if(!(postcode>=0 &&postcode<=9))
        {
           
            
           
           
            return false;
        }
    }
	
	return true;

}

