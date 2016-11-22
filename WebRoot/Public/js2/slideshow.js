(function(b) {
    var d = 0;
    b.fn.skitter = function(e) {
        return this.each(function() {
            new a(this, e, d);
            ++d
        })
    };
    var c = {
        velocity: 1,
        interval: 2500,
        animation: "",
        label: true,
        easing_default: "",
        box_skitter: null,
        time_interval: null,
        images_links: null,
        image_atual: null,
        link_atual: null,
        label_atual: null,
        width_skitter: null,
        height_skitter: null,
        image_i: 0,
        is_animating: false,
        is_hover_box_skitter: false,
        random_ia: null,
        show_randomly: false,
        thumbs: false,
        thumb_width: 70,
        thumb_height: 40,
        caption: "bottom",
        xml: false,
        width_label: null,
        dateIndex:true,
        dates: null,
        monthIndex: null,
        structure: '<div class="slide-month clearfix"><a class="month-nav" id="prev-month" title="上一月发布日历">&lt;</a><a class="month-nav" id="next-month" title="下一月发布日历">&gt;</a><div class="month-text" title="发布日历"><span id="cur-year"></span><span id="cur-month"></span>\u6708</div><div class="month-days" id="month-days"></div></div>'
    };
    b.skitter = function(g, e, f) {
        this.box_skitter = b(g);
        this.timer = null;
        this.settings = b.extend({}, c, e || {});
        this.number_skitter = f;
        this.setup()
    };
    var a = b.skitter;
    a.fn = a.prototype = {};
    a.fn.extend = b.extend;
    a.fn.extend({
        setup: function() {
            var w = this;
            this.settings.width_skitter = parseFloat(this.box_skitter.css("width"));
            this.settings.height_skitter = parseFloat(this.box_skitter.css("height"));
            if (!this.settings.width_skitter || !this.settings.height_skitter) {
                window.console && console.warn("Width or height size is null! - Skitter Slideshow");
                return false
            }
            this.box_skitter.append(this.settings.structure);
                        
            var g = " image_number_select",
                r = 0;
            this.settings.images_links = new Array();
            this.settings.dates = new Array();
            var h = function(x, A, z, u, B) {
                w.settings.images_links.push([A, x, z, u]);
                if (w.settings.thumbs) {
                    var y = "";
                    y = 'width="' + w.settings.thumb_width + '" height="' + w.settings.thumb_height + '"';
                    w.box_skitter.find(".info_slide").append('<span class="image_number' + g + '" rel="' + (r - 1) + '" id="image_n_' + r + "_" + w.number_skitter + '"><img src="' + B + '" ' + y + " /></span> ")
                } else {
                    w.box_skitter.find(".info_slide").append('<span class="image_number' + g + '" rel="' + (r - 1) + '" id="image_n_' + r + "_" + w.number_skitter + '">' + r + "</span> ")
                }
                g = ""
            };
            if (this.settings.xml) {
                b.ajax({
                    type: "GET",
                    url: this.settings.xml,
                    async: false,
                    dataType: "xml",
                    success: function(u) {
                        var x = b("<ul></ul>");
                        b(u).find("skitter slide").each(function() {
                            ++r;
                            var z = (b(this).find("link").text()) ? b(this).find("link").text() : "#";
                            var B = b(this).find("image").text();
                            var A = b(this).find("image").attr("type");
                            var y = b(this).find("label").text();
                            h(z, B, A, y)
                        })
                    }
                })
            } else {
                if (this.settings.json) {
                    $.each(this.settings.json, function() {
                        /* iterate through array or object */
                        ++r;
                        var x = this.url || "#";
                        var z = this.src;
                        var y = this.imgClass || "";
                        var u = this.text || "" ;
                        var A = this.rel || "";
                        if(this.date)w.settings.dates.push(this.date);
                        h(x, z, y, u, A)
                    });
                } else {
                    this.box_skitter.find("ul li").each(function() {
                        ++r;
                        var x = (b(this).find("a").length) ? b(this).find("a").attr("href") : "#";
                        var z = b(this).find("img").attr("src");
                        var y = b(this).find("img").attr("class");
                        var u = b(this).find(".label_text").html();
                        var A = b(this).find("img").attr("rel");
                        h(x, z, y, u, A)
                    })
                }
            }
           
            this.box_skitter.find("ul").hide();

            this.settings.image_atual = this.settings.images_links[this.settings.image_i][0];
            this.settings.link_atual = this.settings.images_links[this.settings.image_i][1];
            this.settings.label_atual = this.settings.images_links[this.settings.image_i][3];
            if (this.settings.images_links.length > 1) {
                this.box_skitter.find("#next-month").click(function(event) {
					
                    /* Act on the event */
                    for (var i = w.settings.monthIndex-1; i >= 0; i--) {
                        var date = w.dateFormat(w.settings.dates[i],"-");
						var index = w.dateFormat(w.settings.dates[w.settings.monthIndex],"-");
						var dateMonth = date.year+"-"+date.month;
						var indexMonth = index.year+"-"+index.month;
						if(dateMonth!==indexMonth){
							w.settings.monthIndex = i;
							return w.dateIndex(w.settings.dates[i]);
						}
                    };

                });
                
                this.box_skitter.find("#prev-month").click(function(event) {
					
                    /* Act on the event */
                    for (var i = w.settings.monthIndex+1; i < w.settings.json.length; i++) {
                        var date = w.dateFormat(w.settings.dates[i],"-");
						var index = w.dateFormat(w.settings.dates[w.settings.monthIndex],"-");
						var dateMonth = date.year+"-"+date.month;
						var indexMonth = index.year+"-"+index.month;
						if(dateMonth!==indexMonth){
						    w.settings.monthIndex = i;
							return w.dateIndex(w.settings.dates[i]);
						}
                    };

                });
                this.box_skitter.find(".image_number").css(w.settings.animateNumberOut);
                this.box_skitter.find(".image_number:eq(0)").css(w.settings.animateNumberActive)
            }

            if(this.settings.dateIndex){
                this.dateIndex(this.settings.json[this.settings.image_i].date);
            }
        },
		  

        shuffleArray: function(h) {
            var e = this;
            var f = new Array();
            var g;
            while (h.length > 0) {
                g = e.randomUnique(0, h.length - 1);
                f[f.length] = h[g];
                h.splice(g, 1)
            }
            return f
        },
        randomUnique: function(g, e) {
            var f;
            do {
                f = Math.random()
            } while (f == 1);
            return (f * (e - g + 1) + g) | 0
        },
        dateFormat:function(date,sep){
            var dates = b.trim(date).split(sep);
            return {
                "year":dates[0],
                "month":dates[1],
                "day":dates[2]
            }
        },
        dateIndex:function(date){
            var _this = this;
            var datestr = this.dateFormat(date,"-");
            
            this.box_skitter.find("#cur-year").html(parseInt(datestr.year,10));
            this.box_skitter.find("#cur-month").html(parseInt(datestr.month,10));
            var days = new Date(datestr.year,datestr.month,0).getDate();
            var daysHtml = "";
            b.each(new Array(days), function(index, val) {
                var dayClass = "has-none";
                var day = index + 1;
                var daystr = (day<10)?("0"+day):day;
                var date = datestr.year + "-" + datestr.month + "-" + daystr;
				var ofhref =""

                if(b.inArray(date,_this.settings.dates)!==-1){
                    dayClass = "has-event";
					ofhref = "href=\"/sofpro/gzyyqt/newdiary/date_result.jsp?StartDate="+date+"\" target=\"_blank\""
                }
                if(date==_this.settings.json[_this.settings.image_i].date){
                    dayClass +=" cur-event";
                }
                daysHtml += "<a title=\"查看"+date+"发布的信息\" class=\""+dayClass+"\""+ofhref+">"+day+"</a>";
            });
            this.box_skitter.find("#month-days").html(daysHtml);
        }
    })
})(jQuery);

