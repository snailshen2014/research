
public class userTable {
	private static final String selectUserField =   
               "(case when a.ONLINE_DUR_LVL=0 or a.ONLINE_DUR_LVL is null then a.LAST3_TOTAL_FEE  "
			  + " when a.ONLINE_DUR_LVL >=3 then ROUND(a.LAST3_TOTAL_FEE/3,2)  "
              + " when  a.ONLINE_DUR_LVL <3  then ROUND(a.LAST3_TOTAL_FEE/a.ONLINE_DUR_LVL,2)  end ) as MB_ARPU,"
			  + " (case when a.ONLINE_DUR_LVL is null then 0 else a.ONLINE_DUR_LVL end )  as MB_ONLINE_DUR,"
			  +	"a.JIAZHI_FLAG as MB_VALUE_LEVEL," //价值等级
			  + " (case when a.DATA_TYPE = '0' then a.CHR_46 "
			  + " when a.DATA_TYPE = '1' then a.KD_CUST_TYPE "
			  + "else '' end ) as CUST_TYPE," //客户类型
			  +"(case when a.DATA_TYPE = '0' then a.BA_S_01 "
			  +"when a.DATA_TYPE ='1' then a.USER_STATUS "
			  + " else '' end ) as USER_STATUS," //用户状态
			  + " ( case when a.OWE_FEE is null then to_char(round(a.KD_NUM_2*100)) else to_char(round(a.OWE_FEE*100)) end )  "
			  + " as OWE_FEE," //是否欠费
			  + "a.ACTIVITY_TYPE as MB_AGREEMENT_TYPE," //合约类型
			  + " a.PR_R_01 as MIX_FLAG,"//是否融合
			  + "a.W3_PROD_ID_1 as ELEC_CHANNEL ,"//是否电子渠道偏好
			  + "a.PROV_ID as PROV_ID," //省份
			  + "a.AREA_NO as AREA_NO ,"//地市
			  + " (case when a.RENT_FEE is null then '0' else to_char(round(a.RENT_FEE*100)) end  ) as RENT_FEE,"//月租费
			  +  "a.CH_C_03 as NETIN_CHANNEL ,"//入网渠道
			  + " (case when a.DATA_TYPE = '0' then a.NUM_24 "
			  + "   else a.KD_CHR_2  end ) as FIRST_OWE_MONTH," //最早欠费月份、宽带欠费最早账龄
			  + "(case when a.DATA_TYPE = '0' then a.EXP_DATE"
			  + " when a.DATA_TYPE='1' then a.KD_DQ_MONTH else '' end) as AGREEMENT_EXPIRE_TIME,"//合约结束时间,到期时间
			  + "  REPLACE(REPLACE(ba_n_11,'\','/'),'\"','')  as CONTACT_NUM," //联系电话
			  + " REPLACE(REPLACE(CUST_NAME,'\','/'),'\"','') as CUST_NAME,"//客户姓名
			  + "a.KD_CHANNEL_ID as NETIN_HALL,"//入网营业厅
			  + "( case when a.KD_INNET_LENGTH is null then 0 else a.KD_INNET_LENGTH end ) as KD_NETIN_MONTHS,"//宽带用户网龄
			  + "a.PRODUCT_CLASS as PRODUCT_CLASS"//价值等级
			  + " (case when a.ACCT_FEE is null then '0' else to_char(round(a.ACCT_FEE*100)) end ) as  ACCT_FEE,"
			  + "a.WENDING_FLAG as WENDING_FLAG,"
			  + "a.CHNL_TYPE4 as CHNL_TYPE4,"
			  + "a.PAY_MODE as PAY_MODE";
	
	public static void main (String[] args) {
		System.out.println(selectUserField);
	}
}
