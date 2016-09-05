package main

import (
	"net/http"
	"log"
	"Moodometer/Server/moodometer"
	"io/ioutil"
	"github.com/golang/protobuf/proto"
)

var howDay = 0

var moods = []moodometer.Mood{}

func main() {
	http.HandleFunc("/", action)
	http.ListenAndServe(":8008", nil)
}

func action(w http.ResponseWriter, r *http.Request) {
	how := r.URL.Path[1:]
	if how == nil {
		log.Print("No day number")
	}
	what := r.URL.Path[2:]
	if what == nil {
		log.Print("No action")
	}
	if how != nil && what != nil {
		howDay = how
		switch what {
		case "add":
			add(w,r)
		case "list":
			list(w,r)
		case "debug":
			debug(w,r)
		}
	}
}

func debug(w http.ResponseWriter, r *http.Request){
	print(moods)
}

func add(w http.ResponseWriter, r *http.Request) {
	mood := new(moodometer.Mood)
	byteProto, err := ioutil.ReadAll(r.Body)
	if err != nil {
		log.Fatal("Read Problem: "+err.Error())
	}
	err = proto.Unmarshal(byteProto, mood)
	if err != nil {
		log.Fatal("Unmarshal Problem: "+err.Error())
	}
	moods = append(moods, mood)

	moodsXday := []moodometer.Mood{}

	// Aujourd'hui en time - le nombre de jours en time


	for _,x := range moods {
		if x.Date >
	}

	meter := new(moodometer.Meter)
	meter.Avg = 0
	for _,m := range moods {
		meter.Avg += m.Score
	}
	meter.Avg /= len(moods)

	byteProto, err = proto.Marshal(meter)
	if err != nil {
		log.Fatal("Marshal Problem: "+err.Error())
	}
	w.Write(byteProto)
}

func list(w http.ResponseWriter, r *http.Request) {

}
