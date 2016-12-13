/*
	 * 作者：孙铖铖
	 * 日期：2016-12-13
	 * 实现：商品发布
	 * 参数：一堆
	 * 触发方式：http://localhost:8080/supermarket/analysis/addGoods?goods={USER_USERNAME:123,PRODUCT_NAME:手机phone,PRODUCT_PRICE:2000,PRODUCT_DESCRIBE:中文商品描述,RRODUCT_TIME:"2016-12-13 10:46:10",PRODUCT_SITE:发布地点,PRODUCT_HIT:50,PRODUCT_FAVOUR:40,PRODUCT_STATUS:TRUE,PRODUCT_TOP:false}
	 * */
	public void addGoods(){
		String jsonContent = this.getPara("goods");
		
		//获取商品属性
//		String PRODUCT_ID;
		String PRODUCT_NAME;
		String str_PRODUCT_PRICE;
		float PRODUCT_PRICE;
		String PRODUCT_DESCRIBE;
//		String PRODUCT_IMAGE	
		String RRODUCT_TIME;
		String PRODUCT_SITE;
		String PRODUCT_HIT;
		String PRODUCT_FAVOUR;
		boolean PRODUCT_STATUS;
		boolean PRODUCT_TOP;
		
		
		//获取用户名
		String USER_USERNAME;
		
		try {
			JSONObject jsonObject = new JSONObject(jsonContent);
			
			PRODUCT_NAME = new String((jsonObject.getString("PRODUCT_NAME")).getBytes("iso-8859-1"),"UTF-8");
			str_PRODUCT_PRICE = jsonObject.getString("PRODUCT_PRICE");
			PRODUCT_PRICE = Float.valueOf(str_PRODUCT_PRICE).floatValue();
			PRODUCT_DESCRIBE = new String((jsonObject.getString("PRODUCT_DESCRIBE")).getBytes("iso-8859-1"),"UTF-8");
			RRODUCT_TIME = jsonObject.getString("RRODUCT_TIME");
			PRODUCT_SITE = new String((jsonObject.getString("PRODUCT_SITE")).getBytes("iso-8859-1"),"UTF-8");
			PRODUCT_HIT = jsonObject.getString("PRODUCT_HIT");
			PRODUCT_FAVOUR = jsonObject.getString("PRODUCT_FAVOUR");
			PRODUCT_STATUS = jsonObject.getBoolean("PRODUCT_STATUS");
			PRODUCT_TOP = jsonObject.getBoolean("PRODUCT_TOP");
			System.out.println("----接口里PRODUCT_STATUS： " + PRODUCT_STATUS+"  PRODUCT_TOP:" + PRODUCT_TOP);
			//获取USER_NAME
			USER_USERNAME = jsonObject.getString("USER_USERNAME");
			
			Record mRecord = new Record().set("PRODUCT_NAME", PRODUCT_NAME)
					.set("PRODUCT_PRICE", PRODUCT_PRICE)
					.set("PRODUCT_DESCRIBE", PRODUCT_DESCRIBE)
					.set("RRODUCT_TIME", RRODUCT_TIME)
					.set("PRODUCT_SITE", PRODUCT_SITE)
					.set("PRODUCT_HIT", PRODUCT_HIT)
					.set("PRODUCT_FAVOUR", PRODUCT_FAVOUR)
					.set("PRODUCT_STATUS", PRODUCT_STATUS)
					.set("PRODUCT_TOP", PRODUCT_TOP);
			boolean res_goods = analysisService.add_Goods(mRecord);
			boolean res_goods_user = analysisService.add_Goods_User(PRODUCT_NAME,USER_USERNAME);
			
			HttpServletResponse response = this.getResponse();
			response.setContentType("application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			
			if(res_goods && res_goods_user){
				writer.write("商品发布成功！");
				writer.flush();
				writer.close();
				this.renderNull();
			}
			else{	//记得处理异常
				writer.write("商品发布失败！");
				writer.flush();
				writer.close();
				this.renderNull();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
