(ns b18
  (:use [trigger.trigger]
        [trigger.synths]
        [trigger.algo]
        [trigger.speech]
        [trigger.samples]
        [trigger.trg_fx] [overtone.core])
  (:require [cutter.interface :as t]))

(future
  (println "Begin loading SuperDirt samples")
  (load-all-SuperDirt-samples)
  (println "Samples loaded"))

(defn add-tts-sample [name path nosamples]

  (println "Begin loading sample " name)

  (add-sample name (string-to-buffer (generate-markov-text path nosamples)))
  (println "Sample" name "loaded") )

0.5625

(set-pattern-duration (/ 1 (* 1 0.5625)))

(set-pattern-delay 0.7)

(cutter.cutter/start :vs "./b18.vs" :fs "./b18.fs" :width 1280 :height 800)

(cutter.cutter/stop-cutter)

(t/cam "0" :iChannel6)

(t/set-cam "0" :fps 1)

(t/cut "../videos/spede_fixed.mp4" "spede1"  50900)

(t/buf "spede1" :iChannel7)

(t/f-buf "spede1")

(t/loop-buf "spede1" true)


(do
  (add-tts-sample "k1"  "generalx2paradisedaqx2.txt" 200)

  (add-tts-sample "k2"  "generalx2paradisedaqx2.txt" 200)

  (add-tts-sample "k3"  "generalx2paradisedaqx2.txt" 200)

  )

;;;;;;;
;;kick;
;;;;;;;
(trg :kick kick
     :in-trg (->> (rep 8 [1 10 100 200])
                  (evr 2 [1 10 20 [0 100 0 100]])
                  (evr 4 (acc  [(range 0 300 30)]))
                  (evr 8 [(fll 16 [0 100])]))
     :in-f3 (->>  [ "fc1"]
                 (rep 8)
                 ;(evr 2  [ "fg2" "fc2" "f bb2" "ff2"])
                 ))

(on-trigger (get-trigger-val-id :kick :in-trg)
            (fn [val]
              ;(println val)
              (let [] (cutter.interface/i-buf "spede1" (int (* 1 val)))  ))
            :kicktrg)

(remove-event-handler :kicktrg)


(on-trigger (get-trigger-val-id :kick :in-f3)
            (fn [val]
              (cutter.interface/set-flt :iFloat1 val)
              )
            :kickf3)

(remove-event-handler :kickf3)


;;;;;;;;
;;snare;
;;;;;;;;

(trg :snare smp)

(pause! :snare)

(trg :snare smp
     :in-trg (->> [(rtm  2 4)]
                  (rep 8)
                  (evr 2 [(rtm 2 4)])
                  (evr 3 (fn [x] (del 0 1 x))))
     :in-step [2]; [1 [1 1] 1.5 1.5]     ; [1.5] [1]       ;":in-trg2"
     :in-loop [0]
     :in-start-pos [0]
     :in-buf (fll 128 ["b sn2" "bsn0"])
     :in-amp [1]       ; [0.15]
     )

(play! :snare)

(volume! :snare 0.25)
