/*******************************************************************************
 * Copyright 2017 Bstek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.itheima.sfbx.framework.rule;

/**
 * @author Jacky.gao
 * @since 2017年8月28日
 */
public class Splash {
	public void print(){
		StringBuilder sb=new StringBuilder();
		sb.append("\n");
        sb.append(". 四方保险规则引擎启动\n");
		sb.append(".....................................................................................................");
		sb.append("\n");
		System.out.println(sb.toString());
	}
	public static void main(String[] args) {
		Splash s=new Splash();
		s.print();
	}
}
