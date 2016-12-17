/**
 * Developer Name : Dennis Chu Wah Tat
 * Developer ID : P7267715
 * 
 * Last Code Change : 
 * 
 ********************
 * Program Overview *
 * ******************
 * This program stores Singapore Attractions[loc], 
 * Interest Type [locType] which the attraction provides,
 * Must see or do [locKey] at the attraction, and 
 * the attractions' ratings.  
 *
 * 
 ***********************
 * Implemented Methods *
 * *********************
 * Method : DisplayMain()
 * Purpose : Main Menu control 
 *
 *Method : DisplayAll 
 *Purpose : Control the display of all attractions
 *
 *Method : DisplaySel()
 *Purpose : Control the displaying of attraction selected via their s/n
 *
 *Method : SearchAt()
 *Purpose : Control the search thru the attraction based on 1 of the 4 attributes
 *
 *Method : FetchLoc()
 *Purpose : Return the each attraction information based on locIndex
 *
 *Method : getInput()
 *Purpose : Process the user's inputs 
 *
 *Method : searchAttractions()
 *Purpose : Search thru the attractions based on the selected attribute 
 *			and user provided keyword
 *
 *
 */

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//Itinerary Recommendation System 


public class IRSys_Ver03 {

//Sample Data

//Location as Master Key
static String[] loc = {"River Safari","Singapore Flyer","Gardens by The Bay",
				 "Orchard Road","Universal Studios","Marina Bay Sands",
				 "Resorts World Sentosa","Night Safari","Singapore Botanic Gardens",
				 "Sri Mariamman Temple","National Gallery"};

//Type of Attraction 
static String[] locType = {"Sightseeing","Sightseeing","Sightseeing",
					 "Shopping", "Excitement","Excitement",
					 "Excitement","Night Life","Sightseeing","Sightseeing","Art"};

//Must see or do  
static String[] locKey = {"Wildlife, Panda Bear, Cruise","Sightseeing","Flower Dome, Cloud Forest",
					"Ion, Mandarin Gallery","Battlestar Galactica, Puss in Boots","Casino, Sky Park Observation Deck",
					"Trick Eye Museum, Adventure Cove","Wildlife, Tram Ride","Flowers","Hindu Temple","Art Gallery"};

//Location Rating
static String[] locRate = {"4.0", "3.5", "3.5",
					 		"4.0", "4.5", "4.0",
					 		"4.5", "4.5","5.0","4.5","4.0"};


	  
//picture carousel
static ImageIcon iconImg= new ImageIcon();

//location attributes
static String[] locAttri = {"Select","Interest Type","Place of Interest","Highlights","Rating"};

static long StartStamp,EndStamp;


	public static void main(String[] args) {
		
		DisplayMain();
		
	}
	
	// Class for Displaying all Attraction
	public static void DisplayMain(){
		
		String optionM ="";
			
		String menuM = " Main Menu \n";
		menuM += "===================\n";
		menuM += "1. Display all Attractions\n";
		menuM += "2. Display Attraction for Selection\n";
		menuM += "3. Search Attractions\n\n";
		menuM += "0. to Quit";
				
		iconImg= new ImageIcon("SingaporeMain.jpg");
		
		optionM = getInput(menuM,"Pls enter 0 to 3", "1|2|3|0", false,1);
				
		// Valid selection made		
		if (optionM.equals("0")){
			//System.out.println("Exit" );
			System.exit(0);
			
		}else if (optionM.equals("1")){
			//System.out.println("1. Display all Attractions" );
			DisplayAll();
			
		}else if (optionM.equals("2")){
			//System.out.println("2. Display Attraction for Selection" );
			DisplaySel();
			
		}else if (optionM.equals("3")){
			//System.out.println("3. Search Attractions" );
			SearchAt();
		}else{
			
			DisplayMain();
		}
	}
		public static void DisplayAll(){
			int locNum=0;
			String optionDA="";
			do{
				String menuDA = "Attraction " + (locNum+1) + " of " + loc.length + "\n";
				menuDA += FetchLoc(locNum);
				menuDA += "Enter N for next attraction\n";
				menuDA += "Enter P for previous attraction\n";
				menuDA += "Enter M to return to main menu\n";
					
				iconImg = new ImageIcon((locNum)+".jpg");
			
			
				optionDA = getInput(menuDA,"Enter N / P for the next/previous \n"
											+ "Enter M for the main menu","n|N|p|P|m|M",false,1);
				
				// Scroll to Next Attraction 		
				if (optionDA.equalsIgnoreCase("n")){
					if (locNum == loc.length - 1)
						JOptionPane.showMessageDialog(null, "End of Collection");
					else
						locNum++;
				
				//Scroll to Previous Attraction	
				}else if (optionDA.equalsIgnoreCase("p")){
					if (locNum == 0)
						JOptionPane.showMessageDialog(null, "Start of Collection");
					else
						locNum--;
				}
				
			}while (optionDA.equalsIgnoreCase("m")==false);
				
			DisplayMain();
		}
		
		// Class for Display Attraction for Selection
		public static void DisplaySel(){
			int locNum=0;
			String optionDS="";
			String xAttraction="";
			
			//Setup Display Menu
			String menuDS = "Attraction Selection Menu\n";
			menuDS += "=====================\n";
			
			for (int i = 0; i < loc.length; i++){
			
				menuDS += (i+1) + ". " + loc[i] + "\n";
				// delimiter "|" is inserted to avoid 
				// picking up 12 or 23 in a string from "123456789" 
				xAttraction += (i+1) +"|";
			}
			
			//System.out.println(xAttraction);
			menuDS += "\n\n Please enter your selection:";			
			iconImg= new ImageIcon("SingaporeMain.jpg");
			
			//Get input back from the getInput()
			optionDS = getInput(menuDS,"Please select one of the attractions listed", 
								xAttraction,false,1);
			
			locNum = Integer.parseInt(optionDS);
			
			
			menuDS = "Attraction " + (locNum) + " of " + loc.length + "\n";
			menuDS += FetchLoc((locNum-1));
			menuDS += "\nEnter M to return to main menu";
			
			iconImg = new ImageIcon((locNum-1)+".jpg");
			
			optionDS = getInput(menuDS,"Invalid Input", "m|M",false,1);
			
			DisplayMain();
			
		}
		
		//Search for specific attraction attributes 
		public static void SearchAt(){	
			String menuSA = "Search by Attributes\n\n";
			menuSA += "Interest Type - Search by Interest \n";
			menuSA += "Place of Interest - Search by Name of Attraction \n";
			menuSA += "Highlights - Search by Highlights at Attraction \n";
			menuSA += "Rating - Search for Attraction\n\n";
			
			//select search attribute 
			String searchAttr = getInput(menuSA,"Select the search attribute","",false,2);
			
			 //System.out.println(searchAttr);
			 
			 String searchkey = getInput("Enter search keyword", "Do Something","",true,1);
				 
			 int[] locFound = searchAttractions(searchAttr,searchkey);
		
			if (locFound.length == 0){
				JOptionPane.showMessageDialog(null, "Sorry, no match found \n Returning to main menu");
			}else{					
				for (int i = 0; i < locFound.length; i++){
					menuSA = "Search Result - " + (i+1) + " of " + locFound.length + "\n";
					menuSA += (FetchLoc(locFound[i]));			
					
					menuSA += "\nEnter M to return to main menu";
					iconImg = new ImageIcon((locFound[i])+".jpg");
					
					searchkey = getInput(menuSA, "Enter M for the main menu\n"
												+ "Enter for the next attraction","m|M",true,1);
						
					if(searchkey.equalsIgnoreCase("m")){
						DisplayMain();
					}	
				}
			}
			
			DisplayMain();
		}
		
		
		// Fetch the location details
		public static String FetchLoc(int locIndex){
			
			String LocInfo;
			
			LocInfo ="========================\n";
			LocInfo += "Types : " + locType[locIndex] + "\n";
			LocInfo += "Attraction : " + loc[locIndex] + "\n";
			LocInfo += "Activities : " + locKey[locIndex] + "\n";
			LocInfo += "Rating : " + locRate[locIndex] + "\n";
			LocInfo += "=====================\n";
			
			return LocInfo;
			
		}
		
		
//		1.ERROR_MESSAGE
//		2.INFORMATION_MESSAGE
//		3. WARNING_MESSAGE
//		4. QUESTION_MESSAGE
//		5. PLAIN_MESSAGE
		
		//Class for validating user input
		public static String getInput(String msg, String errMsg, 
									  String allowedValues, boolean okEmpty,
									  int msgType) {
			
			String s = "";
			
			//separate the allowed values into an array
			String[] values = allowedValues.split("\\|");
			
			boolean inputgood = false;			
			do {
				try{				
					
					switch(msgType){
					
					case 1: // information message
						
						s = (String) JOptionPane.showInputDialog(null,msg,
								"Singapore Attraction Information",JOptionPane.INFORMATION_MESSAGE,
									iconImg,null,"");
							
							if (s != null){ // s could contain space or characters
								s=s.trim();
								
								if((s.length() >= 1)){ //s contains something 
									if (allowedValues.isEmpty()){//input is not restricted
										inputgood=true;
									}else{ //restricted input then check against allowed values
										for (int i = 0; i<values.length;i++){
											if (values[i].equalsIgnoreCase(s)){
												inputgood=true;
											}
										}
									}
								
								}else if ((s.length() < 1)) { //s has only whitespace
									if(okEmpty == true){
										inputgood=true; //empty is allowed
									}
								}

							}else if (!("".equals(s))){ //&& (okEmpty == true)){ //null string detected
								//s=""; // Null detected and is converted if it is okay to be empty 
								DisplayMain();
								//inputgood=true;
							
							}else {
								inputgood=false;
							}
	
						
						break;
						
					case 2: // question message
												
						s = (String) JOptionPane.showInputDialog(null,msg,
								"Singapore Attraction Information",
							    JOptionPane.QUESTION_MESSAGE, 
							    new ImageIcon("SingaporeMain.jpg"), 
							    locAttri,locAttri[0]);
						
						//System.out.println(s);
						
						if(s !=null){
							if (!s.equalsIgnoreCase("Select")){
									inputgood=true;
							} else{
								inputgood=false;
							}
						}else if (!("".equals(s))){ //null string detected
							DisplayMain();
						}
						
						break;
					
					default : // do nothing
						s="";
						break;
					}
					
				}catch (NullPointerException A){
					//System.out.println("NullPointerException has occured");
					JOptionPane.showMessageDialog(null, "Return to main menu");
					DisplayMain();
					//inputgood = false;
				} catch (NumberFormatException B){
					//System.out.println("NumberFormatException has occured");
					JOptionPane.showMessageDialog(null, errMsg);
					inputgood = false;
				}
			}while(inputgood == false);
			//System.out.println(s);
			return s;
			
		}
		
		//static String[] locAttri = {"Interest Type","Place of Interest","Highlights","Rating"};
		//Class for searching the attraction
		public static int[] searchAttractions(String attribute, String keyword) {
			int count = 0;
			int index = 0;
		
		keyword = keyword.toLowerCase();
		
		if(attribute.equalsIgnoreCase("Interest Type"))	{
			//check how many items match the keyword
			for(int i=0;i<locType.length;i++) {
				if(keyword.equalsIgnoreCase(locType[i])) 
					count++;
			}
			int[] searchResult = new int[count];
			//assign the index no of the matching elements to the array
			for(int i=0;i<locType.length;i++) {
				if(keyword.equalsIgnoreCase(locType[i]))  {
					searchResult[index] = i;
					index++;
				}  //end if
			}  //end for
			return searchResult;	
			
		}else if (attribute.equalsIgnoreCase("Place of Interest")){
			for(int i=0;i<loc.length;i++) {
				if(keyword.equalsIgnoreCase(loc[i])) 
					count++;
			}
			int[] searchResult = new int[count];
			//assign the index no of the matching elements to the array
			for(int i=0;i<loc.length;i++) {
				if(keyword.equalsIgnoreCase(loc[i]))  {
					searchResult[index] = i;
					index++;
				}  //end if
			}  //end for
			return searchResult;
			
		}else if (attribute.equalsIgnoreCase("Highlights")){
			for(int i=0;i<locKey.length;i++) {
				if((locKey[i].toLowerCase()).indexOf(keyword) != -1) 
					count++;
			}
			int[] searchResult = new int[count];
			//assign the index no of the matching elements to the array
			for(int i=0;i<locKey.length;i++) {
				if((locKey[i].toLowerCase()).indexOf(keyword) != -1)  {
					searchResult[index] = i;
					index++;
				}  //end if
			}  //end for
			return searchResult;			
			
		}else if (attribute.equalsIgnoreCase("Rating")){
			for(int i=0;i<locRate.length;i++) {
				if (Double.parseDouble(locRate[i]) >= Double.parseDouble(keyword))
					count++;
			}
			int[] searchResult = new int[count];
			//assign the index no of the matching elements to the array
			for(int i=0;i<locRate.length;i++) {
				if(Double.parseDouble(locRate[i]) >= Double.parseDouble(keyword)) {
					searchResult[index] = i;
					index++;
				}  //end if
			
			}  //end for
			return searchResult;	
		} 
		int[] searchResult ={};
		return searchResult;
		
	
	}	
		
}
