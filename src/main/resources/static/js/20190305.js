/**
 * Created by sf on 2018/8/3.
 */
var areaArr = []

var trackHost = 'https://segmentfault.com';
var viewApi = trackHost + '/ad/track/view'
var clickApi = trackHost + '/ad/track/click'

function ajax(opt) {
    opt = opt || {};//opt以数组方式存参，如果参数不存在就为空。
    opt.method = opt.method.toUpperCase() || 'POST';//转为大写失败，就转为POST
    opt.url = opt.url || '';//检查URL是否为空
    opt.async = opt.async || true;//是否异步
    opt.data = opt.data || null;//是否发送参数，如POST、GET发送参数
    opt.success = opt.success || function () {}; //成功后处理方式
    var xmlHttp = null;//定义1个空变量
    if (XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();//如果XMLHttpRequest存在就新建，IE大于9&&非IE有效
    }
    else {
        xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');//用于低版本IE
    }
    var params = [];//定义1个空数组
    for (var key in opt.data){
        params.push(key + '=' + opt.data[key]);//将opt里的data存到push里
    }
    var postData = params.join('&');//追加个& params
    if (opt.method.toUpperCase() === 'POST') {
        xmlHttp.open(opt.method, opt.url, opt.async);//开始请求
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');//发送头信息，与表单里的一样。
        xmlHttp.send(postData);//发送POST数据
    }
    else if (opt.method.toUpperCase() === 'GET') {
        xmlHttp.open(opt.method, opt.url, opt.async);//GET请求
        xmlHttp.send(null);//发送空数据
    }
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {//readyState有5个状态，可以百度一下看看都有什么。当请求完成，并且返回数据成功
            opt.success(xmlHttp.responseText);//返回成功的数据
        }
    };
}

var sTitle="";
window.SFGridAd = {};
SFGridAd.d = function(o) {
    sTitle = o.getAttribute('stitle');
    document.getElementById("gridMapHoverBox").style.display = "block"
}

SFGridAd.e = function(o) {
    sTitle = "";
    document.getElementById("gridMapHoverBox").style.display = "none"
}

SFGridAd.c = function(id) {

    var clickApi2 = clickApi + '?id=' + id

    ajax({url: clickApi2, method: 'GET'})
};

// 这里 data 需要注入
[{"id":"1750000018198085","user_id":"1030000000091606","box_ad_id":"0","started":"1550592000","ended":"1551801600","cycles":"2","status":"0","banner":"\/418\/237\/418237919-5c6b80e3da1d5","link":"https:\/\/fapiao.easyapi.com\/","title":"EasyAPI\u7535\u5b50\u53d1\u7968\uff0c\u8ba9App\uff0c\u5c0f\u7a0b\u5e8f\u7b49\u7acb\u523b\u62e5\u6709\u5f00\u5177\u7535\u5b50\u53d1\u7968\u529f\u80fd\u3002","area_info":{"area":"C7:E7","height":"17","width":"51","left":"34","top":"102","pos":{"rowTop":7,"rowBottom":7,"columnLeft":3,"columnRight":5},"size":3},"created":"1550537326","modified":"1550549232"},{"id":"1750000018309561","user_id":"1030000014460886","box_ad_id":"0","started":"1551196800","ended":"1551801600","cycles":"1","status":"0","banner":"\/157\/288\/1572883837-5c74f79b9a747","link":"https:\/\/enhancer.io","title":"\u4e91\u4e0a\u5f00\u53d1\uff0c\u79c1\u6709\u90e8\u7f72\uff0c\u5199SQL\u5c31\u80fd\u4e00\u7ad9\u5b8c\u6210\u7cfb\u7edf","area_info":{"area":"C2:E2","height":"17","width":"51","left":"34","top":"17","pos":{"rowTop":2,"rowBottom":2,"columnLeft":3,"columnRight":5},"size":3},"created":"1551169384","modified":"1551169569"},{"id":"1750000018316599","user_id":"1030000016263412","box_ad_id":"0","started":"1551283200","ended":"1552492800","cycles":"2","status":"0","banner":"\/274\/450\/2744503121-5c75e62a32865","link":"https:\/\/www.3tee.cn\/index.html?source=segmentfault","title":"\u7eaf\u6d4f\u89c8\u5668\u89c6\u9891\u901a\u8baf\uff08WebRTC\uff09\uff0c\u5feb\u901f\u5d4c\u5165\uff0c\u6d59\u6c5f\u5927\u534e\u80a1\u4efd\u7b49\u90fd\u5728\u7528","area_info":{"area":"N1:O2","height":"34","width":"34","left":"221","top":"0","pos":{"rowTop":1,"rowBottom":2,"columnLeft":14,"columnRight":15},"size":4},"created":"1551230448","modified":"1551230657"},{"id":"1750000018316831","user_id":"1030000002496563","box_ad_id":"0","started":"1551283200","ended":"1552492800","cycles":"2","status":"0","banner":"\/696\/110\/696110750-5c75e95b49d72","link":"https:\/\/www.fundebug.com\/?utm_source=sf_lattice_ad","title":"\u4e00\u884c\u4ee3\u7801\u641e\u5b9aBUG\u76d1\u63a7","area_info":{"area":"G5:I5","height":"17","width":"51","left":"102","top":"68","pos":{"rowTop":5,"rowBottom":5,"columnLeft":7,"columnRight":9},"size":3},"created":"1551231261","modified":"1551231428"}].forEach(function(item) {
    var html = '<area shape="rect" ' +
        'target="_blank" ' +
        'onmouseover="SFGridAd.d(this)" ' +
        'onmouseout="SFGridAd.e(this)" ' +
        'onclick="SFGridAd.c(' + item.id + ')" ' +
        'coords="' + item.area_info.left + ',' + item.area_info.top + ',' + ((+item.area_info.left)+(+item.area_info.width)) + ',' + ((+item.area_info.top)+(+item.area_info.height)) + '" ' +
        'href="' + item.link + '" ' +
        'stitle="' + item.title + '" />'

    areaArr.push(html)
})

document.getElementById('gridsMap').innerHTML = areaArr.join('')

document.getElementById('gridsMap').onmousemove = function(e) {
    var pos = getMousePos(e)

    document.getElementById("gridMapHoverBox").style.left = pos.x + 20 + 'px'
    document.getElementById("gridMapHoverBox").style.top = pos.y + 20 + 'px'

    document.getElementById("gridMapHoverBox").innerHTML = sTitle
}

// 增加 view 统计
setTimeout(function() {
    isShow = document.querySelector('img[src^="https://static.segmentfault.com/sponsor"]').clientHeight > 0
    if (isShow) ajax({url: viewApi, method: 'GET'})
}, 0)

function getMousePos(event) {
    var e = event || window.event;
    var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
    var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
    var x = e.pageX || e.clientX + scrollX;
    var y = e.pageY || e.clientY + scrollY;
    return { 'x': x, 'y': y };
}
