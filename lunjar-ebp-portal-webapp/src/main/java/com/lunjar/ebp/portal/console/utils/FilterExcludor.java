package com.lunjar.ebp.portal.console.utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.lunjar.products.core.utils.Excludor;

public class FilterExcludor extends Excludor{

	private static final String[] DEFAULT_EXCLUES = new String[] { //
			"/**/kindeditor-*-upload", //
					"/**/imageUploador-upload", //
					"/**/getRegions*", //
					"/**/getDictionary*",//
					"/**/validateCode*",//
					"/", //
					"/favicon*",//
					"/invalidSession*",//
					"/trade/asynCommitPage*",//
					"/trade/commitPage*",//
					"/trade/list*",//
					"/trade/detail*",//
					"/**/*.js", //
					"/register"
			};

			private Set<String> excludes;

			public FilterExcludor() {
				if (excludes == null) {
					excludes = new HashSet<String>();
				}
				Collections.addAll(excludes, DEFAULT_EXCLUES);
			}

			//	@PostConstruct
			//	public void init() {
			//		
			//	}

			/**
			 * 注入排除的规则，
			 * <p>
			 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
			 * version: 2010-12-14 下午10:18:14 <br>
			 * 
			 * @param excludes
			 */
			public void setExcludes(Set<String> excludes) {
				//this.excludes = excludes;
				//Collections.addAll(this.excludes, excludes);
				this.excludes.addAll(excludes);
			}

			public Set<String> getExcludes() {
				return excludes;
			}
}
