(function() {
	var Pu, Page;
	
	Page = (function() {
		/*
		 * option
		 * total : 총 데이터 수
		 * count : 한 페이지에 보여질 최대 데이터 수
		 * draw : 화면 그릴 function
		 * button : 조회 버튼 selector
		 * paginate : page 그릴 selector
		 * totalText : totalText 보여 줄 selector
		 * initSet : 최초 로딩 시 page 연산 여부
		 * initCllBck : boolean 값 (미입력시 false)
		 * initEventFun : paging 초기화 전 데이터 조회하는 function 명
		 * pageCount  : block 안의 page 갯수
		 * type : 'search', 'pagination' 둘 중 하나 입력 default는 pagination
		 * 
		 */
		function Page(o) {
			let p = this;
			
			p.total = o.total;
			p.paginate = o.paginate;
			p.count = o.count == undefined ? 10:o.count;
			p.pageCount  = o.pageCount == undefined ? 5:o.pageCount;
			p.draw 		= o.draw;
			p.paginate 	= o.paginate;
			p.initCllBck	= o.initCllBck == undefined ? false:o.initCllBck;
			p.totalText  = o.totalText;
			p.init = init;
			p.setProp = setProp;
			p.initTotal = initTotal;
			
			if(o.initSet) {
				p.init();
			}
			
			if(p.initCllBck) {
				p.init();
				pagination(p);
			}
			
			if(p.initCllBck) {p.draw(1);}
			if(p.total !== 0 && p.total !== undefined && p.total !== null) {pagination(p);}
			
			$(p.totalText).text(p.total);
			
			$(o.button).on('click', function() {
				o.initEventFun(1, function(data) {
					p.total = (data.size == undefined) ? data.length : data.size;
					p.initTotal();
					p.draw(1);
				});
			});
		}
		
		function init() {
			let p = this;
			
			p.totalPage = parseInt(p.total/p.count) + (p.total%p.count == 0 ? 0:1);
			p.startPage	= 1;
			p.startNo   = 0;
			p.endPage 	= p.totalPage > p.pageCount ? p.pageCount:p.totalPage;
			p.nowPage 	= 1;
			p.blockNo	= 1;
			p.prevPage 	= p.startPage == 1 ? 1:p.startPage-1;
			p.nextPage 	= p.endPage == p.totalPage ? p.totalPage:p.endPage+1;
		}
		
		function setProp() {
			let p = this;
			
			p.blockNo = parseInt(p.nowPage/p.pageCount) + (p.nowPage%p.pageCount == 0 ? 0:1);
			p.endPage = p.totalPage > p.blockNo*p.pageCount ? p.blockNo*p.pageCount : p.totalPage;
			p.startPage = (p.blockNo-1) * p.pageCount + 1
			p.startNo = (p.nowPage*p.count) - p.count;
			p.prevPage = p.startPage == 1 ? 1:p.startPage-1;
			p.nextPage = p.endPage == p.totalPage ? p.totalPage:p.endPage+1;
		};
		
		function initTotal() {
			let p = this;
			
			p.init(p);
			pagination(p);
			
			$(p.totalText).text(p.total);
		}
		
		function pagination(p) {
			let i = p.nowPage
			let h = '';
			let t = $(p.paginate);
			
			t.off();
			
			if(p.total != 0) {
				h += '<li><a class="btn-page first" data-index="' + 1 + '"></a></li>';
				h += '<li><a class="btn-page prev" data-index="' + (p.nowPage - 1) + '"></a></li>';

				for(let i=p.startPage; i<p.endPage+1; i++) {
					if(i == p.nowPage) {
						h += '<li><a href="javascript:void(0);" class="active" data-index="' + i + '">' + i + '</a></li>';
					} else {
						h += '<li><a href="javascript:void(0);" data-index="' + i + '">' + i + '</a></li>';
					}
				}
				
				h += '<li><a class="btn-page next" data-index="' + (p.nowPage + 1) + '"></a></li>';
				h += '<li><a class="btn-page last" data-index="' + p.totalPage + '"></a></li>';
			} else {
				h += '<li><a class="btn-page first"></a></li>';
				h += '<li><a class="btn-page prev"></a></li>';
				h += '<li><a>1</a></li>';
				h += '<li><a class="btn-page next"></a></li>';
				h += '<li><a class="btn-page last"></a></li>';
			}

			t.html(h);
			t.on('click', 'a', function() {
				let $t = $(this);
				let i = $t.data('index');

				if(p.total !== 0 && i !== p.nowPage && p.totalPage >= i && i > 0) {
					p.nowPage = i;
					p.draw(i);
					p.setProp();
					pagination(p);
				}
			});
		};
		
		return Page;
	})();
	
	Pu = {
		init : function(o) {
			if(o.type === 'search') {
				if(o.initCllBck) {
					o.initEventFun(1, function(data) {
						o.total = (data.size == undefined) ? data.length : data.size;
						new Page(o);
					});
				} else {
					o.total = 0;
					new Page(o);
				}
			} else {
				return new Page(o);
			}
		}
	};
	
	if (typeof exports !== "undefined" && exports !== null) {
	    exports.Pu = Pu;
	}
	if (typeof window !== "undefined" && window !== null) {
	    window.Pu = Pu;
	} else if (!exports) {
		self.Pu = Pu;
	}
})();
