package com.experian.finance.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.experian.finance.dto.InsertData;
import com.experian.finance.master.Slab;

public class LoadSlabFile {

	public static void main(String[] args) {
		List<Slab> slabColl = new ArrayList<Slab>();
		List<String> slabList = new ArrayList<String>();
		InsertData insertData = new InsertData();

		File slabFile = new File(args[0]);

		Slab slab = null;
		System.out.println(slabFile + " File Exist");

		File fileNameObj = new File(slabFile.getName());
		System.out.println("fileNameObj " + fileNameObj);

		try {
			FileReader fileReader = new FileReader(slabFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String readerString = bufferedReader.readLine();

			while (null != readerString) {
				slabList.add(readerString.toUpperCase());
				readerString = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int a = 1; a < slabList.size(); a++) {

			if (slabList.get(a) == null || slabList.get(a).length() == 0)
				continue;

			String[] splitArr = StringUtils.splitPreserveAllTokens(
					slabList.get(a), ',');

			if (splitArr.length < 12)
				continue;

			slab = new Slab(splitArr);
			slabColl.add(slab);
			slab.nullifyEmptyFields();

			insertData.insertSlab(slab);
		}

	}

}