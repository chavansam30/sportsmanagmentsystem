import { Component, ElementRef, Renderer } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'my-app',
  templateUrl:'app/app.component.html'
})
export class AppComponent  {
	registrationID:any;
	name = 'Angular';
	favoriteSeason:any;
	a:any={}
	firstname:any='';
	lastname:any='';
	middlename:any='';
	addr1:any='';
	addr2:any='';
	nameOfSchoolOrClub:any='';
	addressOfSchoolOrClub:any='';
	address2OfSchoolOrClub:any='';
	dob:any;
	yrs:any='';
	months:any='';
	contactno:any='';
	alternativeno:any='';
	email:any='';
	gender:any='';
	minDate:any;
	maxDate:any;
	nexttext:any='';
	err:any=[];
	pincode:any='';
	city:any='';
	state:any='';
	schoolpincode:any='';
	schoolcity:any='';
	schoolstate:any='';
	record={};
	redirect(pagename: string) {
		this.router.navigate(['/'+pagename]);
	}

	india_states=[
		"Andaman and Nicobar Islands",
		"Andhra Pradesh",
		"Arunachal Pradesh",
		"Assam",
		"Bihar",
		"Chandigarh",
		"Chhattisgarh",
		"Dadra and Nagar Haveli",
		"Daman and Diu",
		"Delhi",
		"Goa",
		"Gujarat",
		"Haryana",
		"Himachal Pradesh",
		"Jammu and Kashmir",
		"Jharkhand",
		"Karnataka",
		"Kerala",
		"Lakshadweep",
		"Madhya Pradesh",
		"Maharashtra",
		"Manipur",
		"Meghalaya",
		"Mizoram",
		"Nagaland",
		"Orissa",
		"Pondicherry",
		"Punjab",
		"Rajasthan",
		"Sikkim",
		"Tamil Nadu",
		"Tripura",
		"Uttaranchal",
		"Uttar Pradesh",
		"West Bengal"
	]
	deciplines=[
					"VOLLEYBALL"
					,"BADMINTON"
					,"SHOOTING"
					,"BASKET BALL"
					,"ATHLETICS"
					,"SWIMMING"
					,"LANGADI"
					,"ARCHERY"
					,"WALL CLIMBING"
					,"CARROM"
					,"KABADDI"
					,"Table Tennis"
					,"FOOTBALL"
					,"KHO KHO"
					,"CHESS"
				];
	arrlist={
  "VOLLEYBALL": {
    B:[{
		2001:"Under 18 Team",
		minage: 18 
	}]
  },
  "BADMINTON": {
    B: [
      {
        2002: "Under 14 SINGLE",
        minage: 14
      },
      {
        2005: "Under 18 SINGLE",
        minage: 18
      }
    ],
    "G": [
      {
        2003: "Under 14 SINGLE",
        minage: 14
      },
      {
        2004: "Under 18 SINGLE",
        minage: 18
      }
    ]
  },
  "SHOOTING": {
    "B": [
      {
        2006: "Under 18 OPEN SIGHT",
        minage: 18
      },
      {
        2008: "Under 18 PIP SIGHT",
        minage: 18
      },
      {
        2010: "Under 18 AIR PISTOL",
        minage: 18
      },
      {
        2012: "Under 20 OPEN SIGHT",
        minage: 20
      },
      {
        2014: "Under 20 PIP SIGHT",
        minage: 20
      },
      {
        2016: "Under 20 AIR PISTOL",
        minage: 20
      }
    ],
    "G": [
      {
        2007: "Under 18 OPEN SIGHT",
        minage: 18
      },
      {
        2009: "Under 18 PIP SIGHT",
        minage: 18
      },
      {
        2011: "Under 18 AIR PISTOL",
        minage: 18
      },
      {
        2013: "Under 20 OPEN SIGHT",
        minage: 20
      },
      {
        2015: "Under 20 PIP SIGHT",
        minage: 20
      },
      {
        2017: "Under 20 AIR PISTOL",
        minage: 20
      }
    ]
  },
  "BASKET BALL": {
    'B': [{
		2018:"Under 19 Team",
		minage: 19 
	}]
  },
  "ATHLETICS": {
    "B": [
      {
        2019: "Under 12 100 MTR",
        minage: 12
      },
      {
        2020: "Under 12 200 MTR",
        minage: 12
      },
      {
        2021: "Under 12 LONG JUMP",
        minage: 12
      },
      {
        2022: "Under 12 HIGH JUMP",
        minage: 12
      },
      {
        2023: "Under 12 SHOT PUT",
        minage: 12
      },
      {
        2029: "Under 14 100 MTR",
        minage: 14
      },
      {
        2030: "Under 14 200 MTR",
        minage: 14
      },
      {
        2031: "Under 14 400 MTR",
        minage: 14
      },
      {
        2032: "Under 14 100 MTR HURDLE",
        minage: 14
      },
      {
        2033: "Under 14 LONG JUMP",
        minage: 14
      },
      {
        2034: "Under 14 HIGH JUMP",
        minage: 14
      },
      {
        2035: "Under 14 SHOT PUT",
        minage: 14
      },
      {
        2036: "Under 14 DISCUSS",
        minage: 14
      },
      {
        2045: "Under 16 100 MTR",
        minage: 16
      },
      {
        2046: "Under 16 200 MTR",
        minage: 16
      },
      {
        2047: "Under 16 400 MTR",
        minage: 16
      },
      {
        2048: "Under 16 110 MTR HURDLE",
        minage: 16
      },
      {
        2049: "Under 16 LONG JUMP",
        minage: 16
      },
      {
        2050: "Under 16 HIGH JUMP",
        minage: 16
      },
      {
        2051: "Under 16 SHOT PUT",
        minage: 16
      },
      {
        2052: "Under 16 DISCUSS",
        minage: 16
      },
      {
        2053: "Under 16 800 MTR ",
        minage: 16
      },
      {
        2054: "Under 16 JAVELLINE",
        minage: 16
      },
      {
        2055: "Under 16 4*100 RELAY",
        minage: 16
      }
    ],
    "G": [
      {
        2024: "Under 12 100 MTR",
        minage: 12
      },
      {
        2025: "Under 12 200 MTR",
        minage: 12
      },
      {
        2026: "Under 12 LONG JUMP",
        minage: 12
      },
      {
        2027: "Under 12 HIGH JUMP",
        minage: 12
      },
      {
        2028: "Under 12 SHOT PUT",
        minage: 12
      },
      {
        2037: "Under 14 100 MTR",
        minage: 14
      },
      {
        2038: "Under 14 200 MTR",
        minage: 14
      },
      {
        2039: "Under 14 400 MTR",
        minage: 14
      },
      {
        2040: "Under 14 100 MTR HURDLE",
        minage: 14
      },
      {
        2041: "Under 14 LONG JUMP",
        minage: 14
      },
      {
        2042: "Under 14 HIGH JUMP",
        minage: 14
      },
      {
        2043: "Under 14 SHOT PUT",
        minage: 14
      },
      {
        2044: "Under 14 DISCUSS",
        minage: 14
      },
      {
        2056: "Under 16 100 MTR",
        minage: 16
      },
      {
        2057: "Under 16 200 MTR",
        minage: 16
      },
      {
        2058: "Under 16 400 MTR",
        minage: 16
      },
      {
        2059: "Under 16 110 MTR HURDLE",
        minage: 16
      },
      {
        2060: "Under 16 LONG JUMP",
        minage: 16
      },
      {
        2061: "Under 16 HIGH JUMP",
        minage: 16
      },
      {
        2062: "Under 16 SHOT PUT",
        minage: 16
      },
      {
        2063: "Under 16 DISCUSS",
        minage: 16
      },
      {
        2064: "Under 16 800 MTR",
        minage: 16
      },
      {
        2065: "Under 16 JAVELLINE",
        minage: 16
      },
      {
        2066: "Under 16 4*100 RELAY",
        minage: 16
      }
    ]
  },
  "SWIMMING": {
    "B": [
      {
        2067: "Under 10 50 MTR FREE",
        minage: 10
      },
      {
        2068: "Under 10 BACK",
        minage: 10
      },
      {
        2069: "Under 10 BREST",
        minage: 10
      },
      {
        2070: "Under 10 BUTTERFLY",
        minage: 10
      },
      {
        2075: "Under 12 50 MTR FREE",
        minage: 12
      },
      {
        2076: "Under 12 BACK",
        minage: 12
      },
      {
        2077: "Under 12 BREST",
        minage: 12
      },
      {
        2078: "Under 12 BUTTERFLY",
        minage: 12
      },
      {
        2083: "Under 14 50 MTR FREE",
        minage: 14
      },
      {
        2084: "Under 14 BACK",
        minage: 14
      },
      {
        2085: "Under 14 BREST",
        minage: 14
      },
      {
        2086: "Under 14 BUTTERFLY",
        minage: 14
      },
      {
        2087: "Under 14 200 MTR IND MIDLAY",
        minage: 14
      }
    ],
    "G": [
      {
        2071: "Under 10 50 MTR FREE",
        minage: 10
      },
      {
        2072: "Under 10 BACK",
        minage: 10
      },
      {
        2073: "Under 10 BREST",
        minage: 10
      },
      {
        2074: "Under 10 BUTTERFLY",
        minage: 10
      },
      {
        2088: "Under 14 50 MTR FREE",
        minage: 14
      },
      {
        2089: "Under 14 BACK",
        minage: 14
      },
      {
        2090: "Under 14 BREST",
        minage: 14
      },
      {
        2091: "Under 14 BUTTERFLY",
        minage: 14
      },
      {
        2092: "Under 14 200 MTR IND MIDLAY",
        minage: 14
      },
      {
        2079: "Under 12 50 MTR FREE",
        minage: 12
      },
      {
        2080: "Under 12 BACK",
        minage: 12
      },
      {
        2081: "Under 12 BREST",
        minage: 12
      },
      {
        2082: "Under 12 BUTTERFLY",
        minage: 12
      }
    ]
  },
  "LANGADI": {
    B: [{
		2093:"Under 14 Team",
		minage: 14 
	}],
    G:[{
		2094:"Under 14 Team",
		minage: 14 
	}]
  },
  "ARCHERY": {
    "B": [
      {
        2095: "Under 10 INDIAN ROUND",
        minage: 10
      },
      {
        2096: "Under 10 RECURVE ROUND",
        minage: 10
      },
      {
        2099: "Under 14 INDIAN ROUND",
        minage: 14
      },
      {
        2100: "Under 14 RECURVE ROUND",
        minage: 14
      },
      {
        2101: "Under 14 COMPOUND ROUND",
        minage: 14
      },
      {
        2105: "Under 17 INDIAN ROUND",
        minage: 17
      },
      {
        2106: "Under 17 RECURVE ROUND",
        minage: 17
      },
      {
        2107: "Under 17 COMPOUND ROUND",
        minage: 17
      },
      {
        2111: "Under 21 INDIAN ROUND",
        minage: 21
      },
      {
        2112: "Under 21 RECURVE ROUND",
        minage: 21
      },
      {
        2113: "Under 21 COMPOUND ROUND",
        minage: 21
      }
    ],
    "G": [
      {
        2097: "Under 10 INDIAN ROUND",
        minage: 10
      },
      {
        2098: "Under 10 RECURVE ROUND",
        minage: 10
      },
      {
        2102: "Under 14 INDIAN ROUND",
        minage: 14
      },
      {
        2103: "Under 14 RECURVE ROUND",
        minage: 14
      },
      {
        2104: "Under 14 COMPOUND ROUND",
        minage: 14
      },
      {
        2108: "Under 17 INDIAN ROUND",
        minage: 17
      },
      {
        2109: "Under 17 RECURVE ROUND",
        minage: 17
      },
      {
        2110: "Under 17 COMPOUND ROUND",
        minage: 17
      },
      {
        2114: "Under 21 INDIAN ROUND",
        minage: 21
      },
      {
        2115: "Under 21 RECURVE ROUND",
        minage: 21
      },
      {
        2116: "Under 21 COMPOUND ROUND",
        minage: 21
      }
    ]
  },
  "WALL CLIMBING": {
    B: [
      {
        2117: "Under 14",
        minage: 14
      },
      {
        2119: "Under 16",
        minage: 16
      }
    ],
    G: [
      {
        2118: "Under 14",
        minage: 14
      },
      {
        2120: "Under 16",
        minage: 16
      }
    ]
  },
  "CARROM": {
    B: [
      {
        2121: "Under 14",
        minage: 14
      },
      {
        2123: "Under 16",
        minage: 16
      }
    ],
    G: [
      {
        2122: "Under 14",
        minage: 14
      },
      {
        2124: "Under 16",
        minage: 16
      }
    ]
  },
  "KABADDI": {
    B: [{
		2125:"Under 14 Team",
		minage: 14 
	}], 
    G:  [{
		2126:"Under 14 Team",
		minage: 14 
	}]
  },
  "Table Tennis": {
    B:[{
		2127:"Under 18 Team",
		minage: 18 
	}] ,
    G:[{
		2128:"Under 18 Team",
		minage: 18 
	}]
  },
  "FOOTBALL": {
    B: [
      {
        2129: "Under 14",
        minage: 14
      },
      {
        2130: "Under 17",
        minage: 17
      }
    ]
  },
  "KHO KHO": {
    B: [{
		2131:"Under 14 Team",
		minage: 14 
	}] ,
   
    G:  [{
		2132:"Under 14 Team",
		minage: 14 
	}] 
  },
  "CHESS": {
    B: [
      {
        2133: "Under 14",
        minage: 14
      },
      {
        2135: "Under 16",
        minage: 16
      }
    ],
    G: [
      {
        2134: "Under 14",
        minage: 14
      },
      {
        2136: "Under 16",
        minage: 16
      }
    ]
  }
}
daylist=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];
monthlist= [ "January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December" ];
list=['ARCHERY','ATHLETICS','BADMINTON','BASKET BALL','CARROM','CHESS','FOOTBALL','KABADDI','KHO KHO','LANGADI','SHOOTING','SWIMMING','Table Tennis','VOLLEYBALL','WALL CLIMBING']
years:any=[];
typeofFN(obj){
	return typeof obj;
}
constructor(public el: ElementRef, public renderer: Renderer,public router:Router) {
    // el.nativeElement.style.backgroundColor = 'yellow';
	//console.log(el);
	this.gender="B";
	this.middlename='';
	this.yrs=0;
	this.months=0;
    renderer.setElementStyle(el.nativeElement, 'backgroundColor', 'yellow');
  }
  isEligible(e){
	  let minage=e[this.getKey(e)[1]];
	  return (minage>this.yrs|| (minage==this.yrs & this.months==0& this.dayofbirth==0))  &&  (minage <=21 || (minage==this.yrs & this.months==0 && this.yrs==21 & this.dayofbirth==0))
  }
	ngOnInit(){
		for(let i=1970;i<=new Date().getFullYear()-10;i++){
			this.years.push(i);
		}
		this.gender="B";
		this.nexttext="Next";
	}
	isLeapyear(){
		if(this.year!=undefined&&(((this.year % 4 == 0) && (this.year % 100 != 0)) || (this.year % 400 == 0)))
			return true;
		return false;
	}
	day:any;
	month:any;
	year:any;
	bankDetails:any="";
	pday:any;
	pmonth:any;
	pyear:any;
	valideDate=false;
	dayofbirth=0;
	calAge(e){
		let d=31;
		if(this.day!=undefined && this.month!=undefined && this.year!=undefined){
			if(this.month=='February'){
			 if((this.isLeapyear() && this.day<30)||this.day<29){
				 d=28;
				 if(this.isLeapyear()){
					d=28;	 
				 }
				 this.valideDate=true;
			 }
			}else if(this.month=='April' || this.month=='June' ||this.month=='September' || this.month=='November' ){
				if(this.day<31){
				 this.valideDate=true;
				 d=31;	 
			 }
			}else{
				this.valideDate=true;
			}
			
			if(this.valideDate){
				console.log(this.month+'/'+ this.day+'/'+this.year);
				this.dob=new Date(this.month+'/'+ this.day+'/'+this.year);
				console.log(this.dob);
				let ageDifMs = Date.now() - new Date(this.month+'/'+ this.day+'/'+this.year).getTime();
				let ageDate = new Date(ageDifMs);
				this.dayofbirth=d-ageDate.getDate();
				this.months=12-(ageDate.getMonth()+1);
				this.yrs=Math.abs(ageDate.getUTCFullYear() - 1970);
				this.valideDate=false;
				this.makeSportList();
			}
			
		}
		
	}
	previous(){
		this.nexttext="Next";
	}
	selectedValue:any;
	selectDecipline(){
		this.go();
	}
	arrylist:any=[];
	addlist:any=[];
	getKey(obj:any){
		return Object.keys(obj);
	}
	add(e:any){
		this.addlist.push({"eventid":this.getKey(e)[0],"Decipline":this.selectedValue,"text":e[this.getKey(e)[0]]});
		return false;
	}
	delet(e){
		let temp=this.addlist.filter((el)=>{return el.eventid!=e});
		this.addlist=temp;
		return false;
	}
	getKeys(obj){
		return Object.keys(obj)
	}
	arrlist1={}
	next(){
		console.log(this.gender);
		this.err=[];
		this.isEmpty(this.firstname,"Name");
		this.isEmpty(this.lastname,"Surname");
		this.isEmpty(this.addr1,"Address");
		this.isEmpty(this.nameOfSchoolOrClub,"Name Of the School/Club");
		this.isEmpty(this.addressOfSchoolOrClub,"School/Club Address");
		this.isEmpty(this.contactno,"Mobile Number");
		this.isEmpty(this.alternativeno,"Emergence Number");
		this.isEmpty(this.email,"Email-Id");
		this.isEmpty(this.state,'State');
		this.isEmpty(this.city,'City');
		this.isEmpty(this.pincode,'Pin Code');
		this.isEmpty(this.schoolstate,'State of school');
		this.isEmpty(this.schoolcity,'City of school');
		this.isEmpty(this.schoolpincode,'Code of school');
		if(this.nexttext=="Submit"){
			this.insertRecord();
		}
		this.arrlist1={};
		/*if(this.err.length==0){
			let temp={};
			for(let i=0;i<this.list.length;i++){
				if(this.arrlist[this.list[i]][this.gender]!=undefined){
					temp[this.list[i]]=this.arrlist[this.list[i]][this.gender].filter((e)=>{return this.isEligible(e);});
				}
			}
			
			this.arrlist1=temp;
			this.nexttext="Submit";
		}*/
		
		return false;
	}
	makeSportList(){
		this.arrlist1={};
		let temp={};
		for(let i=0;i<this.list.length;i++){
			if(this.arrlist[this.list[i]][this.gender]!=undefined){
				temp[this.list[i]]=this.arrlist[this.list[i]][this.gender].filter((e)=>{return this.isEligible(e);});
			}
		}
		this.arrlist1=temp;
	}
	addorremove(event:any,obj:any){
		if(event.target.querySelector('[type="checkbox"]').checked==false){
			this.add(obj);
		}else{
			this.delet(this.getKey(obj)[0]);
		}
	}
	insertRecord() {
		this.address2OfSchoolOrClub="";
		this.addr2="";
		
		let participantDetails={
			"firstname":this.firstname,
			"lastname":this.lastname,
			"middlename":this.middlename,
			"addr1":this.addr1,
			"addr2":this.addr2,
			"state":this.state,
			"city":this.city,
			"pincode":this.pincode,
			"nameOfSchoolOrClub":this.nameOfSchoolOrClub,
			"addressOfSchoolOrClub":this.addressOfSchoolOrClub,
			"address2OfSchoolOrClub":this.address2OfSchoolOrClub,
			"schoolstate":this.schoolstate,
			"schoolcity":this.schoolcity,
			"schoolpincode":this.schoolpincode,
			"dob":this.dob,
			"age":this.yrs+','+this.months,
			"contactno":this.contactno,
			"alternativeno":this.alternativeno,
			"email":this.email,
			"gender":this.gender,
			"bankDetails":this.bankDetails,
			"paymentdate":new Date(this.pmonth+'/'+ this.pday+'/'+this.pyear)
		};
		this.record={
			partidetails:participantDetails,
			games:this.addlist
		}
		let xmlhttp;
		if (window.XMLHttpRequest) {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp=new XMLHttpRequest();
		} else {  // code for IE6, IE5
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		let that=this;
		xmlhttp.open("POST", "poll_vote.php");
		xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlhttp.send("record="+JSON.stringify(this.record));
		xmlhttp.onreadystatechange = ()=>{
			if (xmlhttp.readyState == 4) {
				console.log(xmlhttp);
				this.addedSuccesfully=true;
				this.registrationID=xmlhttp.responseText;
			}
		};
	}
	addedSuccesfully=false;
	
	isEmpty(val:any,errmsg:any){
		(val.trim()=='')?this.err.push(errmsg+" is Required"):'';
	}
}
