// 76f8b6545e34422c93315810e4474b97     my api key

//https://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=76f8b6545e34422c93315810e4474b97


console.log("  Attempting to pull data from newsapi.org !!!");
var url = 'https://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=76f8b6545e34422c93315810e4474b97';
var req = new Request(url);

fetch(req)
    .then(function(response) {
        //console.log(response.json("articles"));

        var articles = response.json("articles");

        for(var i = 0; i < articles.length; i++) {
                console.log(i);
                    //var obj = response.json(i); //json[i];
                    //console.log(obj.title);
                }

        });

console.log("   FINISHED pulling data from newapi.org !!!");

        //console.log(response.json());
        //var thisResponse = response.json();
        //var thisarticles = response.json().articles;
        //var thisheaders = response.headers;
        //var thisurl = response.url;


        //console.log('this responce');
        //console.log(thisResponse);
        //console.log(thisarticles);
        //console.log(thisurl);


        //myData = $.parse(json(response));
        //console.log(myData);

        /*
        for(var i = 0; i < response.json().length; i++) {
        console.log(i);
            var obj = response.json(i); //json[i];
            console.log(obj.title);
        }


        for (var ke in data) {
               if (data.hasOwnProperty(ke)) {
                     alert(data[ke].id);
               }
        }
        */




        //console.log(titleURL);
        //$( "#description" ).attr("src", titleURL)
        //$( "#newstitle" ).attr("articles", title)
        //$( "#newsdescription" ).attr("src", description)
   //});

//





/*
$ npm install newsapi --save


const NewsAPI = require('newsapi');
const newsapi = new NewsAPI('76f8b6545e34422c93315810e4474b97');
// To query /v2/top-headlines
// All options passed to topHeadlines are optional, but you need to include at least one of them
newsapi.v2.topHeadlines({
  //sources: 'bbc-news,the-verge',
  q: 'top-headlines',
  category: 'health',
  language: 'en',
  country: 'us'
}).then(response => {
  console.log(response);
  });
  */












//test to see if this code works

//....curently not working
/*
var request = new XMLHttpRequest();

request.open('GET', 'http://newsapi.org/v2/top-headlines?sources=espn&apiKey=76f8b6545e34422c93315810e4474b97', true);
request.onload = function () {

  // Begin accessing JSON data here
  var data = JSON.parse(this.response);

  if (request.status >= 200 && request.status < 400) {
    console.log('succsess');
    data.forEach(articles => {
      console.log(articles.title);
    });
  } else {
    console.log('error');
  }
}
*/
//request.send();


/*
    var params =
    {
        api_key: "76f8b6545e34422c93315810e4474b97",
        //tag : "jackson 5 " + searchQuery // TODO should be e.g. "jackson 5 dance"
    };



    // make an ajax request for a random GIF
    $.ajax({
        url: 'http://newsapi.org/v2/top-headlines?sources=espn&' // TODO where should this request be sent?

        data: params, // attach those extra parameters onto the request
        success: function(response)
        {
            // if the response comes back successfully, the code in here will execute.
            //console.log(url);
            // jQuery passes us the `response` variable, a regular javascript object created from the JSON the server gave us
            console.log("we received a response!");
            console.log(response);
            //$( "#loading" ).attr("hidden", true);

            var titleURL = Promise.response.Object.articles.description;
            // TODO
            // 1. set the source attribute of our image to the image_url of the GIF
            // 2. hide the feedback message and display the image
            $( "#description" ).attr("src", titleURL)
            //setGifLoadedStatus(true);
        },
        error: function()
        {
            // if something went wrong, the code in here will execute instead of the success function
            //$( "#loading" ).attr("hidden", true);
            // give the user an error message
            $("#feedback").text("Sorry, could not load");
            //setGifLoadedStatus(false);

            //console.log(url);
        }
    });
    */


