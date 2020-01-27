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

(do
  (def fs  "/mnt/Varasto/biisit/Viritystila/b18/b18.fs" )

  (def vs  "/mnt/Varasto/biisit/Viritystila/b18/b18.vs" )

  (def spede_fixed  "/mnt/Varasto/biisit/Viritystila/videos/spede_fixed.mp4" )

  (def uni_fixed  "/mnt/Varasto/biisit/Viritystila/videos/uni_fixed.mp4" )

  (def haps_fixed  "/mnt/Varasto/biisit/Viritystila/videos/hapsiainen_fixed.mp4" )

  (def jkl_fixed  "/mnt/Varasto/biisit/Viritystila/videos/jkl_fixed.mp4")

  (def onnep  "/mnt/Varasto/biisit/Viritystila/videos/onnenp.mp4")

  (def tietoisku_1_fixed  "/mnt/Varasto/biisit/Viritystila/videos/tietoisku_1_fixed.mp4")
  )


;(t/start "./b17.glsl" :width 1280 :height 800 :cams [0 1] :videos ["../videos/jkl_fixed.mp4" "../videos/onnenp.mp4" "../videos/tietoisku_1_fixed.mp4" "../videos/spede_fixed.mp4" "../videos/hapsiainen_fixed.mp4"])


(osc/osc-send oc "/cutter/start" "fs" fs "vs" vs  "width" 1920 "height" 1080)

(osc/osc-send oc "/cutter/stop")

(osc/osc-send oc "/cutter/set-vs-shader" vs )

(osc/osc-send oc "/cutter/set-fs-shader" fs )

(osc/osc-send oc "/cutter/cam" "3" "iChannel1")

(osc/osc-send oc "/cutter/set-cam" "3" "fps" 1)

(osc/osc-send oc "/cutter/cam" "0" "iChannel2")

(osc/osc-send oc "/cutter/cut" jkl_fixed "jkl1" 9925)

(osc/osc-send oc "/cutter/buf" "jkl1" "iChannel3")

(osc/osc-send oc "/cutter/cut" tietoisku_1_fixed "tieto1" 0)

(osc/osc-send oc "/cutter/cut" spede_fixed "spede1" 50900)

(osc/osc-send oc "/cutter/cut" haps_fixed "haps1" 0)

(osc/osc-send oc "/cutter/cut" onnep "onni1" 11800)


(osc/osc-send oc "/cutter/set-float" "iFloat1" 50)

(osc/osc-send oc "/cutter/cam" "0" "iChannel6")

(osc/osc-send oc "/cutter/rec" "0" "suu1")

(osc/osc-send oc "/cutter/set-cam" "0" "fps" 30)

(osc/osc-send oc "/cutter/stop-cam" "0")

(osc/osc-send oc "/cutter/buf" "suu1" "iChannel6")

(osc/osc-send oc "/cutter/fps-buf" "suu1" 60)

(osc/osc-send oc "/cutter/l-buf" "suu1" 30 70)

(osc/osc-send oc "/cutter/unloop-buf" "suu1")

(osc/osc-send oc "/cutter/stop-buf" "suu1")

(osc/osc-send oc "/cutter/cut" spede_fixed "spede" 50900)

(osc/osc-send oc "/cutter/b-buf" "spede")

(osc/osc-send oc "/cutter/f-buf" "spede")

(osc/osc-send oc "/cutter/i-buf" "spede" 100)

(osc/osc-send oc "/cutter/unloop-buf" "spede")

(osc/osc-send oc "/cutter/buf" "spede" "iChannel6")

(osc/osc-send oc "/cutter/fps-buf" "spede" 120)

(osc/osc-send oc "/cutter/l-buf" "spede" 110 150)

(osc/osc-send oc "/cutter/cut" uni_fixed "uni1" 6460)

(osc/osc-send oc "/cutter/buf" "uni1" "iChannel7")

(osc/osc-send oc "/cutter/cut" haps_fixed "haps1" 0)


(do
  (add-tts-sample "k1"  "generalx2paradisedaqx2.txt" 200)

  (add-tts-sample "k2"  "generalx2paradisedaqx2.txt" 200)

  (add-tts-sample "k3"  "generalx2paradisedaqx2.txt" 200)
  )

(do
  (add-sample "uh" (string-to-buffer "UHHHHHHHHAAAAAAAAAAAA"))

  (add-sample "ee" (string-to-buffer "EEEE"))

  (add-sample "aa" (string-to-buffer "AAAAAAAAAA"))

  (add-sample "oo" (string-to-buffer "OOOOOOOOOO"))

  (add-sample "uhea" (string-to-buffer "UHHHHHHHHEEEEEEEAAAAAAA"))

  (add-sample "hah" (string-to-buffer "hah"))

  (add-sample "bah" (string-to-buffer "bah"))

  (add-sample "beer" (string-to-buffer "beer"))

  (add-sample "fear" (string-to-buffer "fear"))

  (add-sample "ahh" (string-to-buffer "AHHHHH!"))

  )

(do
  (trg :tick ping)
  (pause! :tick)
  (trg :tick ping :in-amp [0] :in-trg [(rep 60 1)])
  (play! :tick))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;SETTIIIIIIIIII;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Alkaa UHEA:lla
;;;;

(do
  (trg :uhsmp smp)

  (pause! :uhsmp)

  (trg :uhsmp smp
       :in-trg
       (->>  (slw 16 (fll 16 ["b k2" r "b oo" r "b k3" "b uhea" r]) )
             (evr 5 [(rep 32 "b uh")])
             (evr 3 [r])
             (evr 4 acc)
             (rpl 6 [(rep 16 "b ahh")])
             (rpl 10 ["buhea"])
             (rpl 12 ["bk1"])
                                        ;(evr 8 (fn [x] (fst 32 x)))
             )
       :in-loop
       (->> (rep 8 [0])

            (evr 5  [1])
            )
       :in-buf ":in-trg"
       :in-amp [0.75]
       :in-start-pos
       (->> (rep 8 [0])
                                        ;(evr 5 [(range 0 1600 10)])
            )
       :in-step
       (->> (rep 8 [2])
            (evr 5 (fst 4 [(range -2.5 2.5 0.25) 2 2 1]))
            )
       )

  (trg! :uhsmp :uhsmpe trg-fx-echo
        :in-amp ;(evr 6 [1] (rep 32 [0]))
        (->> (rep 8  [0])
             (evr 6 [1]))
        :in-decay-time [1.1]
        :in-delay-time [0.01])

  (play! :uhsmp)

  )

(pause! :uhsmp)

(volume! :uhsmp 1.25)

(stp :uhsmp)

(sta)


(osc/osc-send oc "/cutter/set-float" "iFloat15" 0)


;;;;;;;;;;;;;;;;
;;:gb:tä mukaan;
;;;;;;;;;;;;;;;;


(println (map find-note-name (chord-degree :i :c2 :major 8)))

(println (map find-note-name (chord :c2 :7sus2)))

(do
  (trg :gb2 vintage-bass)

  (pause! :gb2)

  (trg :gb2
       vintage-bass
       :in-trg
       (seq (map-in
             (->>  ;[1 r [1 1 r r] 1]
              [(rep 4 1)]
              (rep 8)
              (evr 2 [1 [r 1] 1 [1 1]])
              (evr 3 (fn [x] (fst 2 x)))
              (evr 4 (fn [x] (fst 3 x)))
              ;(evr 1 [[(rep 8 1)] (rep 3 1)])
                                        ;(evr 2 [1 [r r 1 1] 1 [1 1] ])
                                        ;(rpl 2  [(rep 4 [(rep 4 1)])])
                                        ;(rpl 3  [[1 1] r [1 1]  r [1 1 1 1] 1 1 1])
                                        ;(evr 4  [[1 1] 1 [1 1] r [1 1 r r] 1 [ r r 1 1] 1])
                                        ;(rpl 5  [1 1 1 [(rep 8 1)]])
                                        ;(rpl 7  [ (acc [(rep 16 1)]) 1 r [1 1] [1 1 1 r] [1 1 1 1] [1 1 1 1] [1 1]])
              )
             scl 0.125))

       :in-gate-select  [0]
       :in-amp [1]
       :in-note (->> ["nc2"]
                     (rep 8)
                     (evr 2 [ "nd2" r  ["ng2" "nbb2" r r] "nc1"])
                     (evr 6 [ "nbb3" r  ["ng3" "nbb3" r r] "nc3"])
                     (evr 7 (fn [x] (fst 4 x)))
                     (evr 3 rev)
                     ;(evr 3 [(fll 8 ["ng2" "nc3" "nbb2" "nbb3"]) "nc1" "ng1" "nc2"])
                     (evr 4 [ "nc2" "nd2"  ["ng2" "nbb2"] "nc1"])
                                        ;(evr 5 [ "nc3" "nc2"  "ng3" "ng2"  "nbb3" "nbb2"  ["ng1" "nbb3"] "nc1"])
                     )
       :in-a [0.0825]
       :in-d (->> [0.093]
                  (rep 8)
                  )
       :in-s
       (->> [0.05]
            (rep 8)
            )
       :in-r
       (->> [0.0275]
            (rep 8)
            )
       )

  (pause! :gb2))

(play! :gb2)

(trg! :gb2 :gbw2 trg-fx-echo
      :in-amp [1]
      :in-decay-time [(/ 0.5625 1)]
      :in-delay-time [(/ 0.5625 5)])

(trg! :gb2 :gb2d trg-fx-distortion2 :in-amount [0.8] )

(stp :gb2d)

(volume! :gb2 1)

(stp :gb2)


(osc/osc-send oc "/cutter/set-float" "iFloat15" 1)


;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;
;;;Setti1 Video;;
;;;;;;;;;;;;;;;;;
(def gbm (audio-bus-monitor (get-out-bus :gb2)))
(def uhbm (audio-bus-monitor (get-out-bus :uhsmp)))


(on-trigger (get-trigger-id :tick :in-trg)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat1" @gbm)
                (osc/osc-send oc "/cutter/set-float" "iFloat2" @uhbm)))
            :smp_obv)

(remove-event-handler :smp_obv)




;;;;; setti2
;;;;Kick kurinaa sekaan
;;; uheat pois

(pause! :uhsmp)

(do
  (trg :kick kick)

  (pause! :kick)

  (trg :kick kick :in-trg
       (->>  [[1 2 (rep 6 r)]  [3 4 r r]]
             (rep 8)
             ;(evr 2  [[2 r 3 4] r [5 6 r 7] 1 [8 9 1 2 ] r [2 3] r] )
             ;(evr 1 (fn [x] (fst 2 x)))
             (evr 2 acc )
             )
       :in-f3 (->> [ "fc1" "fg1" "f f1" "fbb1"]
                   (rep 8)
                   (evr 2  [ "fg1" "fc1" "f bb1" "ff1"]))
       :in-f2 [200]
       :in-f1 (fll 32 [1000 2000])
       :in-amp [0.25])

  (volume! :kick 0.125)

  (play! :kick))

(pause! :kick)

(stp :kick)
;;
(do
  (trg :nh hat2)

  (pause! :nh)

  (trg :nh hat2
       :in-trg
       (->> (rep 16 (fll 16 [0 2]))
            (evr 2 [[1 0] r [1 0] r [1 0] r [1 0] 1])
            (evr 6  (fn [x] (fst 4 x)))
            ;(evr 8 [(rep 32 1)])
            (evr 16 acc)
                                        ;(evr 8 (fn [x] (fst 4 x)))
            )
                                        ;(acc [(rep 64 1)])
       :in-attack [0.01 0.001]
       :in-decay  (->> (rep 16 [0.001])
                       (evr 2  [0.01]))
       :in-amp [1])

  (volume! :nh 0.25)

  (play! :nh))

(pause! :nh)

(pause! :kick)


(osc/osc-send oc "/cutter/set-float" "iFloat15" 1)

;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;
;;;Setti2 Video;;
;;;;;;;;;;;;;;;;;
;;jlk
;;tietoisku
;;spede












;;;;;;;;;;
;;testiä
;;;;;;;
;;kick;
;;;;;;;
(trg :kick kick
     :in-trg (->> ;[120]
                   [10 r [11 12 13 r] 14]
              (rep 4 [(sfl (range 100))] [r])
              ;(rep 4 )
                  (rpl 1 [12 r r  [13 13 r r]])
                  (evr 6 [[[133 11] r] r  [213 r] [312 31 r r]])
                  (rpl 3 [[11 321 r 12 r] r [31 [41 31 12 2131]] r])
                  ;(evr 2 [1 10 20 [0 100 0 100]])
                  ;(evr 4 (acc 10  [(range 0 320 40)]))
                  ;(evr 8 [(fll 8 [0 100])])
                  )
     :in-f3 (->>  [ "fc1"]
                 (rep 8)
                 (evr 2  [ ["fg0"] "fc1" "f bb1" "ff1"])
                 ))

(trg! :kick :kickdist trg-fx-distortion)

(pause! :kick)

(play! :kick)

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
               [r 1 r [1 [1 1]]]
              ;[1 r r r]
              (rep 8)
              (evr 4  [[(rep 8 1)] (rep 7 r)])
              (evr 6  (acc [[(rep 8 1)] (rep 3 r)]))
                  (rpl 2 [r 1 r [r r 1 1]])
                  (rpl 3 [[r [1 1]]  [r 1]])
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

                   (overtone.osc/osc-send oc "/cutter/i-buf" "suu1" 0)

                   ))
            :snaretrg)

(remove-event-handler :snaretrg)

(sta)
