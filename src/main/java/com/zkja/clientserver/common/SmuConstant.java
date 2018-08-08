package com.zkja.clientserver.common;

public class SmuConstant {

	public static String ACTION_LOGIN="80";
	public static String ACTION_LOGOUT="81";
	public static String ACTION_SYSTEM_LOGOUT="99";
	public static String ACTION_HEARTBEAT="82";
	public static String ACTION_SMU_SETUP="83";
	public static String ACTION_REPORT="F2";
	public static String SYSTEM_SMC="SMC";
	public static String ACTION_LOGIN_RES="00";
	public static String ACTION_LOGOUT_RES="01";
	public static String ACTION_HEARTBEAT_RES="02";
	public static String ACTION_SMU_SETUP_RES="03";

	public static String OPCODE_SP_DEV_STATUS="sp_dev_status";
	public static String OPCODE_SP_DEV_WARNINFO="sp_dev_warnInfo";
	public static String OPCODE_SP_REPORTINFO="sp_reportInfo";
	public static String OPCODE_SP_RUNINFO="sp_runInfo";
	
	public static String ACTION_IMPRISON_DISABLE="84";
	public static String ACTION_IMPRISON_ENABLED="85";
	public static String ACTION_STARTREPORT="F1";
	public static String ACTION_STOPREPORT="F3";
	public static String ACTION_SMUINFO="F4";
	public static String ACTION_SYSCONF="IP";
	public static String ACTION_FENCECONF="FE";
	public static String ACTION_LIGHTCONF="LE";
	public static String ACTION_WN="WN";
	public static String ACTION_REPORT_NEW = "F5";
	public static String ACTION_REPORT_RUN = "F9";
	public static String ACTION_REPORT_REALTIME = "F10";
	public static String ACTION_REPORT_FB = "FB";
	public static String ACTION_HEALTHPARAMS = "FC";
	public static String ACTION_BLUETOOTHSETUP = "FD";
	public static String ACTION_SMU_F6 = "F6";
	public static String ACTION_SMU_F7 = "F7";
	public static String ACTION_SMU_F8 = "F8";
	/**
	 * 	在线
	 */
	public static Integer ON_LINE= 1;
	/**
	 * 	离线
	 */
	public static Integer OFF_LINE= 0;
	/**
	 * 	手机+蓝牙手表
 	 */
	public static String PHONE_PID= "00000000000000";
	/**
	 * 	GSM手表
 	 */
	public static String WATCH_PID= "11111111111111";
	/**
	 * 	成功
 	 */
	public static String IMPRISON_SUCCECC= "1";
	/**
	 * 	失败
 	 */
	public static String IMPRISON_FAILURE= "0";
	/**
	 * 	1:SOS求救报警；
 	 */
	public static String WARN_SOSWARNFALG= "1";
	/**
	 * 	2:蓝牙断开报警；
 	 */
	public static String WARN_BLUETOOTH_DISCONNECT= "2";
	/**
	 * 	3:腕带拆解报警；
 	 */
	public static String WARN_STRAPS_DISASSEMBLE= "3";
	/**
	 * 	4:蓝牙断开报警+腕带拆解报警；
 	 */
	public static String WARN_BLUETOOTH_AND_STRAPS= "4";
	/**
	 * 	5:手机低电报警；
 	 */
	public static String WARN_PHONE_LOW_BATTERY= "5";
	/**
	 * 	6:手表低电报警；
 	 */
	public static String WARN_WATCH_LOW_BATTERY= "6";
	/**
	 * 	7:手机低电报警+手表低电报警;
 	 */
	public static String WARN_PHONE_AND_WATCH= "7";
	/**
	 * 	8:围栏报警;
 	 */
	public static String WARN_FENCE= "8";
	/**
	 * 9:拆解报警(围栏报警以后PID开盖报警);
	 */
	public static String WARN_DISASSEMBLE= "9";
	public static String ACTION_SMU_LOGOUT_ANSWER = "01";
	public static String ACTION_SMU_IMPRISON_ANSWER = "04";
	public static String ACTION_SMU_RELIEVE_ANSWER = "05";
	public static String ACTION_SMU_STARTREPORT_ANSWER = "A1";
	public static String ACTION_SMU_STOPREPORT_ANSWER = "A3";
	public static String ACTION_SMU_SMUINFO_ANSWER = "A4";
	public static String ACTION_SMU_SYSCONF_ANSWER = "S1";
	public static String ACTION_SMU_FENCE_ANSWER = "S2";
	public static String ACTION_SMU_LIGHT_ANSWER = "S3";
	public static String ACTION_SMU_WN_ANSWER = "W1";
	public static String ACTION_SMU_F6_ANSWER = "A6";
	public static String ACTION_SMU_F7_ANSWER = "A7";
	public static String ACTION_SMU_F8_ANSWER = "A8";
	public static String ACTION_SMU_F9_ANSWER = "A9";
	public static String ACTION_SMU_G0_ANSWER = "B0";
	public static String ACTION_SMU_AA_ANSWER = "AA";
	public static String ACTION_SMU_F2_ANSWER = "A2";
	public static String ACTION_SMU_A1_ANSWER = "A1";
	public static String ACTION_SMU_F5_ANSWER = "A5";
	public static String ACTION_REPORT_REALTIME_ANSWER = "A10";
	public static String ACTION_REPORT_AB_ANSWER = "AB";
	public static String ACTION_HEALTHPARAMS_ANSWER = "AC";
	public static String ACTION_BLUETOOTHSETUP_ANSWER = "AD";
	
	public static String ACTION_VALID = "A";

	
}
