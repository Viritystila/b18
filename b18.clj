(ns b18
  (:use [trigger.trigger]
        [trigger.synths]
        [trigger.algo]
        [trigger.speech]
        [trigger.samples]
        [trigger.trg_fx]
        [overtone.core])
  (:require  [overtone.osc :as osc]))

(future
  (println "Begin loading SuperDirt samples")
  (load-all-SuperDirt-samples)
  (println "Samples loaded"))


;;Muista vb!!!!

(defn add-tts-sample [name path nosamples]

  (println "Begin loading sample " name)

  (add-sample name (string-to-buffer (generate-markov-text path nosamples)))
  (println "Sample" name "loaded") )

(def oc (osc/osc-client "localhost" 44100))

(set-pattern-duration (/ 1 (* 1 0.5625)))

(set-pattern-delay 0)

(do
  (def fs  "/mnt/Varasto/biisit/Viritystila/b18/b18.fs" )

  (def vs  "/mnt/Varasto/biisit/Viritystila/b18/b18.vs" )

  (def spede_fixed  "/mnt/Varasto/biisit/Viritystila/videos/spede_fixed.mp4" )

  (def uni_fixed  "/mnt/Varasto/biisit/Viritystila/videos/uni_fixed.mp4" )

  (def haps_fixed  "/mnt/Varasto/biisit/Viritystila/videos/hapsiainen_fixed.mp4" )

  (def jkl_fixed  "/mnt/Varasto/biisit/Viritystila/videos/jkl_fixed.mp4")

  (def onnep  "/mnt/Varasto/biisit/Viritystila/videos/onnenp.mp4")

  (def tietoisku_1_fixed  "/mnt/Varasto/biisit/Viritystila/videos/tietoisku_1_fixed.mp4")

  (def tieto2  "/mnt/Varasto/biisit/Viritystila/videos/tieto2.mp4")

  (def suu  "/mnt/Varasto/biisit/Viritystila/videos/suu.mp4")

  (def sormi "/mnt/Varasto/biisit/Viritystila/videos/sormileikit.mp4" )
  )



(osc/osc-send oc "/cutter/start" "fs" fs "vs" vs  "width" 1280 "height" 800)

(osc/osc-send oc "/cutter/stop")

(osc/osc-send oc "/cutter/set-vs-shader" vs )

(osc/osc-send oc "/cutter/set-fs-shader" fs )

;;;; Cam 3
(osc/osc-send oc "/cutter/cam" "3" "iChannel1")

(osc/osc-send oc "/cutter/set-cam" "3" "fps" 1)

(osc/osc-send oc "/cutter/stop-cam" "3")

;;;;; cam 0
(osc/osc-send oc "/cutter/cam" "0" "iChannel2")

(osc/osc-send oc "/cutter/stop-cam" "0")


;;;;;;; jkl
(osc/osc-send oc "/cutter/cut" jkl_fixed "jkl1" 9925)

(osc/osc-send oc "/cutter/buf" "jkl1" "iChannel3")

;;;;;;;;;;; tieto1
(osc/osc-send oc "/cutter/cut" tietoisku_1_fixed "tieto1" 0)

(osc/osc-send oc "/cutter/buf" "tieto1" "iChannel4")

(osc/osc-send oc "/cutter/fps-buf" "tieto1" 120)

(osc/osc-send oc "/cutter/unloop-buf" "tieto1")

(osc/osc-send oc "/cutter/l-buf" "tieto1" 1 115)

(osc/osc-send oc "/cutter/stop-buf" "tieto1")

;;;;;;;;;; spede1
(osc/osc-send oc "/cutter/cut" spede_fixed "spede1" 50900)

(osc/osc-send oc "/cutter/buf" "spede1" "iChannel5")

(osc/osc-send oc "/cutter/stop-buf" "spede1")


;;;;;;;;;;; haps1
(osc/osc-send oc "/cutter/cut" haps_fixed "haps1" 0)

(osc/osc-send oc "/cutter/buf" "haps1" "iChannel6")

;;;;;;;;;;;;;; onni1
(osc/osc-send oc "/cutter/cut" onnep "onni1" 11800)

(osc/osc-send oc "/cutter/buf" "onni1" "iChannel7")

;;;;;;;;;;;;;;; tieto2
(osc/osc-send oc "/cutter/cut" tieto2 "tieto2" 1)

(osc/osc-send oc "/cutter/buf" "tieto2" "iChannel8")

(osc/osc-send oc "/cutter/fps-buf" "tieto2" 120)

(osc/osc-send oc "/cutter/unloop-buf" "tieto2")

(osc/osc-send oc "/cutter/l-buf" "tieto2" 0 75)

(osc/osc-send oc "/cutter/f-buf" "tieto2")

(osc/osc-send oc "/cutter/stop-buf" "tieto2")

;;;;;;;;;;;;;; suu1
(osc/osc-send oc "/cutter/cut" suu "suu1" 0)

(osc/osc-send oc "/cutter/buf" "suu1" "iChannel9")

(osc/osc-send oc "/cutter/fps-buf" "suu1" 120)

(osc/osc-send oc "/cutter/unloop-buf" "suu1")

(osc/osc-send oc "/cutter/l-buf" "suu1" 0 60)

;;;sormileikit

(osc/osc-send oc "/cutter/cut" sormi "sormi1" 0)

(osc/osc-send oc "/cutter/buf" "sormi1" "iChannel10")




(osc/osc-send oc "/cutter/stop-buf" "tieto1")

(osc/osc-send oc "/cutter/stop-buf" "jk1")



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
  (trg :tick ping :in-amp [0] :in-trg [(rep 1 20)])
  (play! :tick))

(stp :tick)

(trg :sync ping :in-trg [1])

(pause! :sync)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;SETTIIIIIIIIII;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Setti 1;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Alkaa UHEA:lla
;;;;

(do
  (trg :uhsmp smp)

  (pause! :uhsmp)

  (trg :uhsmp smp
       :in-trg
       (->   (fll 16 ["b k2" r "b oo" r "b k3" "b uhea" r])
             (slw 16)
             ;;(evr 5 (acc [(rep "b k1" 16)]))
             ;;(evr 3 [r])
             ;;(evr 4 acc)
             ;;(rpl 6 [(rep  "b ahh" 4)])
             ;;(rpl 10 ["buhea"])
             ;;(rpl 12 ["bk1"])
             ;;(evr 1 (fn [x] (fst x 32)))
             )
       :in-loop
       (-> (rep [0] 8)

            (evr 5  [1])

            ;(evr 1 [1])
            )
       :in-buf ":in-trg"
       :in-amp [0.75]
       :in-start-pos
       (-> (rep [0] 8)
                                        ;(evr 5 [(range 0 1600 10)])
            )
       :in-step
       (->  (rep [2] 8)
            (evr 5 (fst  [(range -2.5 2.5 0.25) 2 2 1] 4))
            ;(evr 1 [-2])
            )
       )


  (volume! :uhsmp 1.25)


  (trg! :uhsmp :uhsmpe trg-fx-echo
        :in-amp ;(evr 6 [1] (rep 32 [0]))
        (-> (rep [0] 6)
             (evr 6 [1]))
        :in-decay-time [1.1]
        :in-delay-time [0.1])

  )

(play! :uhsmp)



(pause! :uhsmp)


(stp :uhsmp)

(sta)


(osc/osc-send oc "/cutter/set-float" "iFloat15" 0)


;;;;;;;;;;;;;;;;
;;:overp mukaan;
;;;;;;;;;;;;;;;;


(println (map find-note-name (chord-degree :i :c2 :major 8)))

(println (map find-note-name (chord :c2 :7sus2)))

(do

  (trg :overp overpad)

  (pause! :overp)

  (trg :overp overpad
       :in-trg (map-in (->
                     [1 1 1 1]
                     (rep 32)
                     (evr 5 fst)
                     (evr 5 acc))
                       scl 0.125)
       :in-amp [0.2]
       :in-note (-> ["nc2"]
                     (rep 32)
                     (evr 1 [ "nc2" r  ["ng2" "nbb2" r r] "nc1"])
                     (evr 2 [ "nd2" r  ["ng2" "nbb2" r r] "nc2"])
                     ;(evr 4 (fn [x] (fst  x 2)))
                     ;(evr 3 [(fll 8 ["ng2" "nc3" "nbb2" "nbb3"]) "nc1" "ng1" "nc2"]
                     (evr 6 [ "nc2" "nd2"  ["ng1" "nbb3"] "nc1"])
                     (evr 3 [ [r r r r "nc2" "nd2"] ["nd3" "nc2" r r r r]])
                     ;(evr 16  [ "nd2" "nc3"  "ng3" "ng2"  "nbb3" "nbb2"  ["ng1" r] "nc1"])
                     (evr 32 slw)
                     )
       :in-attack [0.01]
       :in-decay  [0.1]
       :in-sustain [1.1]
       :in-release (-> [1.01]
                        (rep 32)
                        (rpl 32 [0 0 0 1]))
       :in-gate-select [0])


  (trg! :overp :ope trg-fx-echo
        :in-amp [0.95]
        :in-decay-time [(/ 0.5625 1)]
        :in-delay-time [(/ 0.05625 5)])


  (trg! :overp :oped trg-fx-distortion2 :in-amount [0.92] )

  )


(play! :overp)



(stp :ope)

(stp :overp)

;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;
;;;Setti1 Video;;
;;;;;;;;;;;;;;;;;
(def overpm (audio-bus-monitor (get-out-bus :overp)))

(def uhbm (audio-bus-monitor (get-out-bus :uhsmp)))


(on-trigger (get-trigger-id :tick :in-trg)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat1" @overpm)
                (osc/osc-send oc "/cutter/set-float" "iFloat2" @uhbm)))
            :smp_obv)

(remove-event-handler :smp_obv)


;;Setti2
;;Villen setti
;;;
;;overp mukana
(pause! :uhsmp)

(do
  (trg :singlesmp smp)
  (pause! :singlesmp)
  (trg :singlesmp smp
       :in-trg (-> [[(rep  "b bass23"2) (rep r 2)] r r r]
                    (rep 8)
                    (evr 3  [[(rep "b bass23" 2) (rep r 2)] r "b bass23" r]))
       :in-loop (rep  [0] 8) ;[1] (rep [0] 7)
       :in-buf ":in-trg"
       :in-step  (rep [2] 8) ;[2] (rep [2] 7)
       :in-amp [0.25])

  )

(play! :singlesmp)

;;;;;
;;;Setti2 video
;;;;;;
(osc/osc-send oc "/cutter/set-float" "iFloat15" 1)



;;;;;;;;;;;;;;
;;;;;Setti3
;; Villen bittisetii
;;;;;;;;;;;;;;

(pause! :uhsmp)

(pause! :overp)

(pause! :singlesmp)

;(play! :overp)



;;
(do
  (trg :nh hat2)

  (pause! :nh)

  (trg :nh hat2
       :in-trg
       (->  (fll 16 [1 0])
            (rep 16)
            (evr 2 [[1 1] r [1 1] r [1 1] r [1 1] 1])
            (evr 6  (fn [x] (fst x 4)))
                                        ;(evr 8 [(rep 32 1)])
            (rpl 16 [0 0 0 0])
            (evr 8 acc)
            (evr 7 dcl)
            (evr 1 slw)
                                        ;(evr 8 (fn [x] (fst 4 x)))
            )
                                        ;(acc [(rep 64 1)])
       :in-attack [0.01 0.001]
       :in-decay  (->  [0.001]
                        (rep 16)
                       (evr 2  [0.01]))
       :in-amp [1])

  (volume! :nh 2.25)

  )

(play! :nh)

(pause! :nh)


(osc/osc-send oc "/cutter/set-float" "iFloat15" 2)

;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;
;;;Setti3 Video;;
;;;;;;;;;;;;;;;;;

(on-trigger (get-trigger-val-id :nh :in-trg)
            (fn [val]
              (overtone.osc/osc-send oc "/cutter/set-float" "iFloat4" val)
                (overtone.osc/osc-send oc "/cutter/i-buf" "tieto2" (int 0))
              )
            :nhtrg)

(remove-event-handler :nhtrg)

;;;;;;;;;;;;;;;;;;
;;;;Setti4
;;Spede villeltä
;;;;;;;;;;;;;;;;;;;

;Tähän pientä kickrumpua synckkiin villen spedeen
(osc/osc-send oc "/cutter/set-float" "iFloat15" 3)

(pause! :nh)

(do
  (trg :singlesmp smp)
  (pause! :singlesmp)
  (trg :singlesmp smp
       :in-trg (-> [ r r r [(rep "b sn0" 2)] ]
                   (rep 16)
                   (evr 4  [r r r ["b sn0" r r "b sn3"]])
                   (evr 8 [r r r r r r r ["b sn0" r "b sn2" "b sn3"]])
                                        ;(evr 3  [[(rep 2 "b bass23") (rep 2 r)] r "b bass23" r])
                    )
       :in-loop [0]
       :in-buf ":in-trg"
       :in-step [1]
       :in-amp [1.5])

  )

(play! :singlesmp)


;;;;;;;;;;;;;;;
;;;;Setti4 video
;;;;;;;;;;;;;;;


(def smbbus (audio-bus-monitor (get-out-bus :singlesmp)))


(on-trigger (get-trigger-val-id :singlesmp :in-trg)
            (fn [val]
              ;(println (int val))
              (let []
                (overtone.osc/osc-send oc "/cutter/i-buf" "spede1" (int val))
                (overtone.osc/osc-send oc "/cutter/set-float" "iFloat5" val)

                   ))
            :sings)

(remove-event-handler :sings)

;;;;;;;;;;;;;;;;;;;;
;;;Setti5;;;;;
;;;;;;;;;;;;;


(pause! :kick)

(pause! :nh)

(pause! :singlesmp)

(do
  (trg :kick kick)

  (pause! :kick)

  (trg :kick kick :in-trg
       (->  ;[[1 2 (rep 6 r)]  [3 4 r r]]
             [[1 2 (rep r 6)]]
             (rep 32)
             (evr 3 [r])
             (evr 2 [ r 3 4 r])
             (rpl 16 [(rep  1 32)])
             (rpl 20 [[1 2 (rep r 6)] [r [14 50]]])
             (rpl 28 [1 [4 5] [(rep r 4)] 3 30  ])
             (evr 14 [r])
             ;(evr 2  [[2 r 3 4] r [5 6 r 7] 1 [8 9 1 2 ] r [2 3] r] )
             (evr 2 (fn [x] (fst x 2)))
             ;(evr 1 acc )
             )
       :in-f3 (-> [ "fc1" "fg1" "f f1" "fbb1"]
                   (rep 8)
                   (evr 2  [ "fg1" "fc1" "f bb1" "ff1"])
                   (evr 4  [ "fd2" "fd3" "f c2" "ff3"]))
       :in-f2 (-> [200]
                   (rep 32)
                   (evr 16 [ 2000]))
       :in-f1 (fll 32 [1000 2000 100])
       :in-amp [0.25])

  (volume! :kick 1.75)

  )

(play! :kick)



(pause! :kick)

(stp :kick)
(lss)

(do
  (trg :ksmp smp)

  (pause! :ksmp)

  (trg :ksmp smp
       :in-trg (-> [r]
                    (rep 32)
                    (evr 16 [1 1 1 1 1 1 1 [(rep 1 64)]])
                    (rpl 33 (slw [1 1 1 1] 4))
                    ;(evr 1 acc)
                                        ;(evr 8 [(rep 32 1)])
                    )
       :in-buf  ["b k1"]
       (rep [r] 15)
       ["b k2"]
       (rep [r] 15)
       ["b k3"]
       (rep  [r] 16)
       :in-step (-> [2]
                     (rep 32)
                     (evr 24 (slw [(sir 32 2.5 1 32) 1])) ;[2]
)
       :in-loop [1]
       :in-start-pos  (-> [1]
                           (rep 8)
                           (evr 2 [(range 0 40000 5000)])
                           )
       :in-amp [1.0])

  (volume! :ksmp 4.75)

  (trg! :ksmp :ksmp_p trg-fx-pitch-shift
        :in-pitch-ratio [0.9]
        :in-pitch-dispersion [0.1])


  (pause! :ksmp)
  )

(play! :ksmp)

(do
  (trg :sups supersaw)
  (pause! :sups)

  (trg :sups supersaw
       :in-freq
       (->
        (-> [ "fc0" "fg0" "f d0" "fbb0"]
            (rep 16)
            (evr 2  [ "fg0" "fc0" "f bb0" "fd0"])
            (evr 4  [ "fd1" "fd1" "f c1" "fd2"])
            (evr 3 rev)
            (evr 8 [(range  (mhz :c0)  (mhz :d3) 10)] )
            )
        (rep 2)
        (evr 4 rev))

       :in-amp [1.5]
       )


  )

(play! :sups)



;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;
;;;Setti5 Video;;
;;;;;;;;;;;;;;;;;
;;jlk
;;tietoisku 1


(osc/osc-send oc "/cutter/set-float" "iFloat15" 4)


(on-trigger (get-trigger-val-id :kick :in-trg)
            (fn [val]
              ;(println val)
              (let []
                (overtone.osc/osc-send oc "/cutter/i-buf" "tieto1" (int 0))

                   ))
            :kicktrg)

(remove-event-handler :kicktrg)


(def kickbus (audio-bus-monitor (get-out-bus :kick)))


(on-trigger (get-trigger-id :tick :in-trg)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat6" @kickbus) ))
            :kickbus)

(remove-event-handler :kickbus)



;;;;;
;;;;;;;;;;;
;;Setti6;;;
;;;;;;;;;;;;
;;;;;;;;;;
(pause! :sups)
(pause! :kick)
(pause! :ksmp)
(osc/osc-send oc "/cutter/set-float" "iFloat15" 5)



;;;                                        ;Villen rave

 ;;;                                       ; jotain biitiä videosynkkin

(do
  (trg :singlesmp smp)
  (pause! :singlesmp)
  (trg :singlesmp smp
       :in-trg (-> [ r r r [(rep "b sn0" 2)] ]
                   (rep 16)
                   (evr 4  [r r r ["b sn0" r r "b sn3"]])
                   (evr 8 [r r r r r r r ["b sn0" r "b sn2" "b sn3"]])
                                        ;(evr 3  [[(rep 2 "b bass23") (rep 2 r)] r "b bass23" r])
                    )
       :in-loop [0]
       :in-buf ":in-trg"
       :in-step [1]
       :in-amp [1.5])

  )

(play! :singlesmp)

;;;;;;;;;;;;;;;,
;;Setti 6 video
;;;;;;;;;;
;;Sormileikki
;;onnepyörä



;;;;;;;;;
;;Setti 7
;;;;;;;;;, jatkuu sujuvasti edellisestä
(osc/osc-send oc "/cutter/set-float" "iFloat15" 6)

(lss)



 (do
  (trg :softh soft-hat)
  (pause! :softh)
  (trg :softh noise-snare
       :in-trg (->
                [r]
                (rep 8)
                (evr 3  [ [(rep r 7) 1] 1 ])
                (evr 2  [1 r r [1 1]])
                (rpl 4  [[(rep 1 4)] 1 r [1 1 r r]])
                (evr 7  [(rep 16 1)])
                )
       :in-freq  (-> ["fc6" "fg6" "f d6" "fbb6"]
                      (rep 32)
                      (evr 2  ["fd8" [ r r "fg7" "f d5"] r "fbb7"] ))
       :in-attack (-> [0.1]
                       (rep 8)
                       (evr 8 [0.01])
                       (rpl 4 [0.001])
                       (rpl 5 [0.001])))

  (volume! :softh 5)

  )

(play! :softh)


(do
  (trg :bassd smp)

  (pause! :bassd)


  (trg :bassd smp
   :in-trg
   (->
    ;["bbd1"]
    [[r (rep "b bd1" 2) r ]  ["b sn1"  "b bd4"] [r r "b bd1" r] [ "b sn2" r "b bd1" r]]
         (rep 16)
         ;(evr 2  [["b bd1"]  [ "b sn1" "b bd3"] [r r (rep "b bd1" 1) r] [ "b sn2" "b bass15" r r]])
         ;(evr 4 [[(rep "b bd1" 4)]   [(rep  "b sn1" 4)] [r r  "b bass23" r] [ "b sn2" r "b bd1" r]])
         ;;(evr 7  ["b bd1"   (acc [(rep "b sn2" 8)]) [ "b bd2" r "b bd1" r] [ "b sn1" r "b bd1" r]])
         ;(evr 8  (sfl (fll 16 [r "b bass15"  "b sn1" r   "b bass23" r  "b sn0" r])))
         ;;(evr 15 acc)
         (evr 4 rev)
         )
   :in-step (-> [2]
                 (rep 16)
                 ;;(evr 3 [-2])
                 )
   :in-loop (-> [0]
                 (rep 16)
                 ;;(evr 3 [1])
                 )
   :in-start-pos [0]
   :in-buf ":in-trg"
   )

  (volume! :bassd 4.25)

  (trg! :bassd :bassde trg-fx-echo
        :in-decay-time [(/ (/ 1 0.5626)  2)]
        :in-delay-time  [(/ (/ 1 0.5626)  50)]
        :in-amp (-> [0.1]
                    (rep 16)
                    (evr 16 [0.125 0.5 0.75 1 0.75 0.5 1 0.125])))

  )


(play! :bassd)


;(stp :bassd)
;;;


(pause! :kick)
(pause! :sups)
(pause! :ksmp)


(println (map find-note-name (chord :d2 :7sus2)))

;;;;;;;;;;;;;;;;;;;;;;;
;;;Setti 7 video
;;;;;;;;;
;;Suu

(on-trigger (get-trigger-val-id :bassd :in-trg)
            (fn [val]
              ;(println val)
              (let []
                (overtone.osc/osc-send oc "/cutter/i-buf" "suu1" (int 0))

                   ))
            :bassdtrg)

(remove-event-handler :bassdtrg)


(def softhbus (audio-bus-monitor (get-out-bus :softh)))


(on-trigger (get-trigger-id :tick :in-trg)
            (fn [val]
              (let []
                ;(println @softhbus)
                (osc/osc-send oc "/cutter/set-float" "iFloat7" @softhbus) ))
            :softhbus)

(remove-event-handler :softhbus)

;;;;;;;;;;;,,
;; Setti 8
;;;;;;;;;;;;
(osc/osc-send oc "/cutter/set-float" "iFloat15" 7)

(pause! :bassd)
(pause! :softh)


(do
  (trg :ks1 ks1)

  (pause! :ks1)

  (trg :ks1
       ks1
       :in-trg
       [(rep "n a5" 8)]
       [(rep "n b5" 8) ]
       [(rep "n d5" 8)]
       [(rep "n e4" 2)  (rep "n c#3" 2)  (rep "n b2" 2)  (rep "n b1" 2)]
       (sfl [(fll 32  [r r r "n b3"])])
       [(rep "n d3" 16)]
       [(rep "n a3" 16)]
       (fst ["n c#2" "n e3" "n b3" "n b2"] 2)
       :in-dur [10.5]
       :in-amp [1]
       :in-note ":in-trg"
       :in-decay (-> [0.75]
                     (rep 8)
                     (evr 4 [100]))
       )

  (volume! :ks1 1.5)

  (trg! :ks1 :ks1d trg-fx-feedback-distortion
        :in-noise-rate [1]
        :in-decay (-> [0.2]
                      (rep 8)
                      (evr 4 [0.001]))
        :in-delay-t [0.01]
        :in-boost [0.91]
        )

  )

(play! :ks1)

(pause! :ks1)

(stp :ks1d)


(do
  (trg :vb vintage-bass)

  (pause! :vb)

  (trg :vb
       vintage-bass
       :in-trg
       [(rep "n a5" 16)]
       [(rep "n b5" 16)]
       [(rep "n d5" 16)]
       [(rep "n e4" 2)  (rep "n c#3" 2)  (rep "n b2" 2)  (rep "n b1" 2)]
       [(rep "n b1" 16)]
       [(rep "n d1" 16)]
       [(rep "n a1" 16)]
       (fst ["n c#2" "n e3" "n b3" "n b2"] 1)
                                        ;[(rep 16  "n e3")]
                                        ;[(rep 16 "n b3")]
                                        ;[(rep 16 "n c4")]
                                        ;(fst 1 ["n c#2" "n e2" "n b3" "n b4"])
       :in-gate-select (rep [0] 3) [1 0] ;(rep 4 [0])
       :in-amp [0.08]
       :in-note  ":in-trg"
       :in-a [0.01]
       :in-d [1.9]
       :in-s [1.1]
       :in-r [1.85])

  (volume! :vb 0.8)

  (trg! :vb :distro trg-fx-distortion2 :in-amount [0.97])

  (trg! :vb :reverb trg-fx-reverb :in-sig-a [0.53])

  )

(play! :vb)

(play! :ksmp)

(lss)
(pause! :ksmp)
(pause! :sups)
(pause! :softh)
(pause! :bassd)
(pause! :vb)
(pause! :ks1)
(pause! :kick)

(stp :vb)

(println (map find-note-name (chord :d2 :7sus2)))


(do
  (trg :tb303sn tb303)

  (pause! :tb303sn)

  (trg :tb303sn
       tb303
       :in-trg
       (->  (fst ["n c2" r r ["n c2" "n d2"]])
             (rep 16)
             (evr 2  (fst ["n e2" ["n d3"  "n c2"] r r]))
             (rpl 3  (fst [(rep "n e3" 2)  (rep "n c#3" 2)  (rep "n b2" 2)  (rep "n b1" 2)]))
             ;(evr 1 fst)
             (evr 4 rev)
             ;(evr 1 acc)
             ;(evr 3  [["n e2" "n a2" r r] [r "n d3" "n e3" "n d2"]])
             ;(evr 4  ["n d3" ["nd3" "nb2" "ne3" "na2"]])
             ;(evr 5  ["n a3" ["ne3" "na2" "nd3" "nc2"]])
             ;(evr 1 slw)
             ;(evr 2 slw)
             ;(evr 4 rev)
             ;(evr 3 fst)
             )
       :in-amp [1]
       :in-note  ":in-trg"
       :in-gate-select [1]
       :in-attack [0.01]
       :in-decay [0.19]
       :in-sustain [0.25]
       :in-release [0.1273]
       :in-r [0.9]
       :in-cutoff [1600]
       :in-wave
       (rep [0] 4)

       )


  (volume! :tb303sn 2)

  )

(play! :tb303sn)


;;;Setti 8 video
;;;;;;
;;;Hapsiainen

(def vbbus (audio-bus-monitor (get-out-bus :tb303sn)))


(on-trigger (get-trigger-id :tick :in-trg)
            (fn [val]
              (let []
                ;(println @softhbus)
                (osc/osc-send oc "/cutter/set-float" "iFloat8" @vbbus) ))
            :vbbus)

(remove-event-handler :vbbus)

@vbbus


;;;;;;;;;;;;;;;;
;;;tmp


(trg :singlesmp smp
     :in-trg (acc (fst [2 r 3 4 5 r r 6] 8) 4) (rep [r] 7)
      (dcl (fst [1 2 3 4 5 6 7 8] 8) 2) (rep [r] 7)
      :in-loop (rep [0] 8) [1] (rep [0] 7)
     :in-buf (fll 8 ["b hc0" "b sn1" "b bd0"])
     :in-step  (rep [2] 8) [2] (rep [2] 7)
     :in-amp [1.25])

(pause! :singlesmp)

(play! :singlesmp)



(println (map find-note-name (chord :d2 :7sus2)))


(do
  (trg :tb303sn tb303)

  (pause! :tb303sn)

  (trg :tb303sn
       tb303
       :in-trg
       (->  (fst ["n c2" r r ["n c3" "n d2"]])
             (rep 16)
             ;(evr 2  (fst ["n e0" ["n d0"  "n c0"]]))
             ;(evr 1 slw)
             (evr 4 rev)
             ;(evr 3  [["n e2" "n a2" r r] [r "n d3" "n e3" "n d2"]])
             ;(evr 4  ["n d3" ["nd3" "nb2" "ne3" "na2"]])
             ;(evr 5  ["n a3" ["ne3" "na2" "nd3" "nc2"]])
             ;(evr 1 slw)
             ;(evr 2 slw)
             ;(evr 4 rev)
             ;(evr 3 fst)
             )
       :in-amp [1]
       :in-note  ":in-trg"
       :in-gate-select [1]
       :in-attack [0.01]
       :in-decay [0.19]
       :in-sustain [0.25]
       :in-release [0.73]
       :in-r [0.9]
       :in-cutoff [2600]
       :in-wave
       (rep [0] 4)

       )


  (volume! :tb303sn 8)

  )

(play! :tb303sn)

(stp :tb303sn)

(sta)
