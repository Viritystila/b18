(ns b18
  (:use [trigger.trigger]
        [trigger.synths]
        [trigger.algo]
        [trigger.speech]
        [trigger.samples]
        [trigger.trg_fx]
        [overtone.core]
        ;[overtone.osc]
        )
  (:require ;[cutter.interface :as t]
            [overtone.osc :as osc])
  )

(future
  (println "Begin loading SuperDirt samples")
  (load-all-SuperDirt-samples)
  (println "Samples loaded"))

(defn add-tts-sample [name path nosamples]

  (println "Begin loading sample " name)

  (add-sample name (string-to-buffer (generate-markov-text path nosamples)))
  (println "Sample" name "loaded") )

(def oc (osc/osc-client "localhost" 44100))

(set-pattern-duration (/ 1 (* 1 0.5625)))

(set-pattern-delay 0.7)

(def fs  "/mnt/Varasto/biisit/Viritystila/b18/b18.fs" )

(def vs  "/mnt/Varasto/biisit/Viritystila/b18/b18.vs" )

(def spede_fixed  "/mnt/Varasto/biisit/Viritystila/videos/spede_fixed.mp4" )

(def uni_fixed  "/mnt/Varasto/biisit/Viritystila/videos/uni_fixed.mp4" )

(def haps_fixed  "/mnt/Varasto/biisit/Viritystila/videos/haps_fixed.mp4" )


(osc/osc-send oc "/cutter/start" "fs" fs "vs" vs  "width" 1280 "height" 800)

(osc/osc-send oc "/cutter/stop")

(osc/osc-send oc "/cutter/set-vs-shader" vs )

(osc/osc-send oc "/cutter/set-fs-shader" fs )

(osc/osc-send oc "/cutter/set-float" "iFloat1" 50)

(osc/osc-send oc "/cutter/cam" "0" "iChannel6")

(osc/osc-send oc "/cutter/rec" "0" "suu1")

(osc/osc-send oc "/cutter/set-cam" "0" "fps" 10)

(osc/osc-send oc "/cutter/stop-cam" "0")

(osc/osc-send oc "/cutter/buf" "suu1" "iChannel6")

(osc/osc-send oc "/cutter/fps-buf" "suu1" 120)

(osc/osc-send oc "/cutter/l-buf" "suu1" 36 37)

(osc/osc-send oc "/cutter/unloop-buf" "suu1")

(osc/osc-send oc "/cutter/cut" spede_fixed "spede" 50900)

(osc/osc-send oc "/cutter/b-buf" "spede")

(osc/osc-send oc "/cutter/f-buf" "spede")

(osc/osc-send oc "/cutter/i-buf" "spede" 100)

(osc/osc-send oc "/cutter/buf" "spede" "iChannel7")

(osc/osc-send oc "/cutter/cut" uni_fixed "uni1" 6460)

(osc/osc-send oc "/cutter/buf" "uni1" "iChannel7")

(osc/osc-send oc "/cutter/cut" haps_fixed "haps1" 0)


(do
  (add-tts-sample "k1"  "generalx2paradisedaqx2.txt" 200)

  (add-tts-sample "k2"  "generalx2paradisedaqx2.txt" 200)

  (add-tts-sample "k3"  "generalx2paradisedaqx2.txt" 200)

  )

;;;;;;;
;;kick;
;;;;;;;
(trg :kick kick
     :in-trg (->> ;[10 r [11 12 13 r] 14]
              (rep 4 [(sfl (range 100))] [r])
              (rep 4 )
                  ;(rpl 1 [12 r r  [13 13 r r]])
                  (evr 6 [[[133 11] r] r  [213 r] [312 31 r r]])
                  ;(rpl 3 [[11 321 r 12 r] r [31 [41 31 12 2131]] r])
                  ;(evr 2 [1 10 20 [0 100 0 100]])
                  ;(evr 4 (acc 10  [(range 0 320 40)]))
                  ;(evr 8 [(fll 8 [0 100])])
                  )
     :in-f3 (->>  [ "fc1"]
                 (rep 8)
                 (evr 2  [ ["fg0"] "fc1" "f bb1" "ff1"])
                 ))

(trg! :kick :kickdist trg-fx-distortion)

(on-trigger (get-trigger-val-id :kick :in-trg)
            (fn [val]
              ;(println val)
              (let []
                ;(cutter.interface/i-buf "spede1" (int (* 1 val)))

                   (overtone.osc/osc-send oc "/cutter/i-buf" "spede" (int val))

                   ))
            :kicktrg)

(remove-event-handler :kicktrg)


(on-trigger (get-trigger-val-id :kick :in-f3)
            (fn [val]
              ;(cutter.interface/set-flt :iFloat1 val)

              (overtone.osc/osc-send oc "/cutter/set-float" "iFloat1" val)

              )
            :kickf3)

(remove-event-handler :kickf3)


;;;;;;;;
;;snare;
;;;;;;;;

(trg :snare smp)

(pause! :snare)

(trg :snare smp
     :in-trg (->>
                                        ;[r 1 r [1 [1 1]]]
              [1 r r r]
              (rep 8)
              (evr 4  [[(rep 16 1)] (rep 7 r)])
               (evr 6  [[(rep 16 1)] (rep 3 r)])
                  ;(rpl 2 [r 1 r [r r 1 1]])
                  ;(rpl 3 [[r [1 1]]  [r 1]])
                  ;(evr 2 [[r 1] r 1 r])
                  ;(evr 4 [ [r 1] 4])
                  )
     :in-step [2]; [1 [1 1] 1.5 1.5]     ; [1.5] [1]       ;":in-trg2"
     :in-loop [0]
     :in-start-pos [0]
     :in-buf (fll 128 ["b sn2" "bsn1" "bsn0"])
     :in-amp [1]       ; [0.15]
     )

(play! :snare)

(volume! :snare 0.125)

(sta)


(on-trigger (get-trigger-val-id :snare :in-trg)
            (fn [val]
              ;(println val)
              (let []
                ;(cutter.interface/i-buf "spede1" (int (* 1 val)))

                   (overtone.osc/osc-send oc "/cutter/i-buf" "spede" 0)

                   ))
            :snaretrg)

(remove-event-handler :snaretrg)
