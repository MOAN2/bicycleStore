(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["uni_modules/uview-ui/components/u-row-notice/u-row-notice"],{"11f5":function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var i=o(e("a34a")),u=o(e("b79e"));function o(t){return t&&t.__esModule?t:{default:t}}function a(t,n,e,i,u,o,a){try{var r=t[o](a),c=r.value}catch(s){return void e(s)}r.done?n(c):Promise.resolve(c).then(i,u)}function r(t){return function(){var n=this,e=arguments;return new Promise((function(i,u){var o=t.apply(n,e);function r(t){a(o,i,u,r,c,"next",t)}function c(t){a(o,i,u,r,c,"throw",t)}r(void 0)}))}}var c={name:"u-row-notice",mixins:[t.$u.mpMixin,t.$u.mixin,u.default],data:function(){return{animationDuration:"0",animationPlayState:"paused",nvueInit:!0,show:!0}},watch:{text:{immediate:!0,handler:function(n,e){this.vue(),t.$u.test.string(n)||t.$u.error("noticebar组件direction为row时，要求text参数为字符串形式")}},fontSize:function(){this.vue()},speed:function(){this.vue()}},computed:{textStyle:function(){var n={};return n.color=this.color,n.animationDuration=this.animationDuration,n.animationPlayState=this.animationPlayState,n.fontSize=t.$u.addUnit(this.fontSize),n}},mounted:function(){this.init()},methods:{init:function(){this.vue(),t.$u.test.string(this.text)||t.$u.error("noticebar组件direction为row时，要求text参数为字符串形式")},vue:function(){var n=this;return r(i.default.mark((function e(){var u;return i.default.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return 0,u=0,e.next=3,t.$u.sleep();case 3:return e.next=5,n.$uGetRect(".u-notice__content__text");case 5:return u=e.sent.width,e.next=8,n.$uGetRect(".u-notice__content");case 8:e.sent.width,n.animationDuration="".concat(u/t.$u.getPx(n.speed),"s"),n.animationPlayState="paused",setTimeout((function(){n.animationPlayState="running"}),10);case 12:case"end":return e.stop()}}),e)})))()},nvue:function(){return r(i.default.mark((function t(){return i.default.wrap((function(t){while(1)switch(t.prev=t.next){case 0:case"end":return t.stop()}}),t)})))()},loopAnimation:function(t,n){},getNvueRect:function(t){},clickHandler:function(t){this.$emit("click")},close:function(){this.$emit("close")}}};n.default=c}).call(this,e("543d")["default"])},"8bbe":function(t,n,e){},a9da:function(t,n,e){"use strict";e.d(n,"b",(function(){return u})),e.d(n,"c",(function(){return o})),e.d(n,"a",(function(){return i}));var i={uIcon:function(){return Promise.all([e.e("common/vendor"),e.e("uni_modules/uview-ui/components/u-icon/u-icon")]).then(e.bind(null,"8a63"))}},u=function(){var t=this,n=t.$createElement,e=(t._self._c,t.__get_style([t.textStyle])),i=["link","closable"].includes(t.mode);t.$mp.data=Object.assign({},{$root:{s0:e,g0:i}})},o=[]},b80b:function(t,n,e){"use strict";var i=e("8bbe"),u=e.n(i);u.a},cded:function(t,n,e){"use strict";e.r(n);var i=e("11f5"),u=e.n(i);for(var o in i)"default"!==o&&function(t){e.d(n,t,(function(){return i[t]}))}(o);n["default"]=u.a},de66:function(t,n,e){"use strict";e.r(n);var i=e("a9da"),u=e("cded");for(var o in u)"default"!==o&&function(t){e.d(n,t,(function(){return u[t]}))}(o);e("b80b");var a,r=e("f0c5"),c=Object(r["a"])(u["default"],i["b"],i["c"],!1,null,"0a3daeae",null,!1,i["a"],a);n["default"]=c.exports}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'uni_modules/uview-ui/components/u-row-notice/u-row-notice-create-component',
    {
        'uni_modules/uview-ui/components/u-row-notice/u-row-notice-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("de66"))
        })
    },
    [['uni_modules/uview-ui/components/u-row-notice/u-row-notice-create-component']]
]);
