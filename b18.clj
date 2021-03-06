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
  (let [txt (generate-markov-text path nosamples)
        _ (add-sample name (string-to-buffer txt))]
    (println "Sample" name "loaded")
    txt))

(def oc (osc/osc-client "localhost" 44100))


(set-pattern-duration (/ 1 (* 1 0.5625)))

(set-pattern-delay 1.5)

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



;(osc/osc-send oc "/cutter/connect" 57111)


(osc/osc-send oc "/cutter/stop-buf" "tieto1")

(osc/osc-send oc "/cutter/stop-buf" "jk1")



(do
  (def k1t (add-tts-sample "k1"  "generalx2paradisedaqx2.txt" 200))

  (def k2t (add-tts-sample "k2"  "generalx2paradisedaqx2.txt" 200))

  (def k3t (add-tts-sample "k3"  "generalx2paradisedaqx2.txt" 200))
  nil
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

(play! :sync)

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
       (->   (fll ["b k2" r "b oo" r "b k3" "b uhea" r] 16)
             (slw 16)
             ;;(evr 5 (acc [(rep "b k1" 16)]))
             (evr 3 [r])
             (evr 4 [r])
             (evr 5 [r])
             ;(evr 1 acc)
             (rpl 6 [(rep  "b ahh" 4)])
             (rpl 10 ["buhea"])
             (rpl 12 ["bk1"])
             ;;(evr 1 (fn [x] (fst x 32)))
             )
       :in-loop
       (-> (rep [1] 8)

            (evr 5  [1])

            ;(evr 1 [1])
            )
       :in-buf ":in-trg"
       :in-amp [0.75]
       :in-start-pos
       (-> (rep [0] 8)
           (evr 5 [(range 0 1600 10)])
            )
       :in-step
       (->  (rep [2] 8)
            (evr 5 (fst  [(range -2.5 2.5 0.25) 2 2 1] 4))
            ;(evr 1 [-2])
            )
       )


  (volume! :uhsmp 1.75)


  (trg! :uhsmp :uhsmpe trg-fx-echo
        :in-amp ;(evr 6 [1] (rep 32 [0]))
        (-> (rep [0] 6)
             (evr 6 [1]))
        :in-decay-time [1.051]
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
       :in-amp [0.10]
       :in-note (-> ["nc2"]
                    (rep 8)
                    (cnc (rep ["nd2"] 8))
                    (cnc (rep ["ng2"] 8))
                     ;(evr 1 [ "nc2" r  ["ng2" "nbb2" r r] "nc1"])
                     ;(evr 2 [ "nd2" r  ["ng2" "nbb2" r r] "nc2"])
                     ;(evr 4 (fn [x] (fst  x 2)))
                    (evr 3 [(fll ["ng2" "nc5" "nbb4" "nbb3"] 8) "nc1" "ng1" "nc2"] )
                     ;(evr 6 [ "nc2" "nd2"  ["ng1" "nbb3"] "nc1"])
                     ;(evr 3 [ [r r r r "nc2" "nd2"] ["nd3" "nc2" r r r r]])
                     ;(evr 16  [ "nd2" "nc3"  "ng3" "ng2"  "nbb3" "nbb2"  ["ng1" r] "nc1"])
                     ;(evr 1  acc)
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
        :in-delay-time [(/ 0.05625 200)])


  (trg! :overp :oped trg-fx-distortion2 :in-amount [0.93] )

  )

(play! :overp)

(pause! :overp)

;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;
;;;Setti1 Video;;
;;;;;;;;;;;;;;;;;

(nth  (clojure.string/split k1t '#" ") (rand-int (count  (clojure.string/split k1t '#" "))))


(on-trigger (get-trigger-vol-id :uhsmp)
            (fn [val]
              (let []
                                        ;(println val)
                ;(osc/osc-send oc "/cutter/write" "aaaaa" 0 220 10 100 0.2 0.4 20 10 1)
                (osc/osc-send oc "/cutter/set-float" "iFloat1" val)
                ))
            :uhsmp-vol)

(remove-event-handler :uhsmp-vol)


(on-trigger (get-trigger-val-id :uhsmp :in-trg)
            (fn [val]
              (let [ tx (nth  (clojure.string/split k1t '#" ") (rand-int (count  (clojure.string/split k1t '#" "))))]
                (osc/osc-send oc "/cutter/write" tx 400 520 10 100 0.2 0.4 20 10 1)))
            :uhsmp)



(on-trigger (get-trigger-vol-id :overp)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat2" val)
                ))
            :overp)

(remove-event-handler :overp)

;;Setti2
;;Villen setti
;;;
;;overp mukana
(pause! :uhsmp)
(pause! :overp)

(do
  (trg :singlesmp smp)
  (pause! :singlesmp)
  (trg :singlesmp smp
       :in-trg (->   ;["b uh"]
                    [[(rep  "b bass23" 1) (rep r 3)] r r r]
                    (rep 8)
                    (evr 3  [[(rep "b bass23" 2) (rep r 2)] r "b bass23" r])
                    (evr 2 [r])
                    (evr 8  [[(rep "b bass23" 8) (rep r 2)] r "b bass23" r])
                    )
       :in-loop (rep  [0] 8) ;[1] (rep [0] 7)
       :in-buf ":in-trg"
       :in-step  (rep [2] 8) ;[2] (rep [2] 7)
       :in-amp [0.35])

  )

(play! :singlesmp)

(do (trg :hz haziti-clap)
    (pause! :hz)

    (trg :hz haziti-clap
         :in-trg  (-> [(rep 1 8)]
                      (rep 8)))

(volume! :hz 0.2)
)

(play! :hz)

(volume! :hz 0.2)

;;;;;
;;;Setti2 video
;;;;;;
(osc/osc-send oc "/cutter/set-float" "iFloat15" 1)



(on-trigger (get-trigger-vol-id :singlesmp)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat3" val)
                ))
            :singlesmp)

(remove-event-handler :singlesmp)



;(osc/osc-send oc "/cutter/set-trigger" (get-trigger-vol-id :singlesmp) "iFloat3")

;(osc/osc-send oc "/cutter/unset-trigger" (get-trigger-vol-id :singlesmp) "iFloat3")


;;;;;;;;;;;;;;
;;;;;Setti3
;; Villen bittisetii
;;;;;;;;;;;;;;
(pause! :hz)

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
       (->  (fll [1 0] 16)
            (rep 16)
            ;(evr 2 [[1 1] r [1 1] r [1 1] r [1 1] 1])
            ;(evr 6  (fn [x] (fst x 4)))
                                        ;(evr 8 [(rep 32 1)])
            ;(rpl 16 [0 0 0 0])
            (evr 8 acc)
            (rpl 8 fst)
            (rpl 8 fst)
            ;(evr 7 dcl)
            (evr 1 slw)
                                        ;(evr 8 (fn [x] (fst 4 x)))
            )
                                        ;(acc [(rep 64 1)])
       :in-attack (-> [0.01 0.001]
                      (rep 16)
                      (evr 2 [0.01 [(rep r 7) 1] ]))
       :in-decay  (->  [0.001]
                        (rep 16)
                       (evr 2  [0.01]))
       :in-amp [1])

  (volume! :nh 1.25)


  (trg! :nh :nhe trg-fx-echo
        :in-amp ;(evr 6 [1] (rep 32 [0]))
        (-> (rep [1] 6)
             (evr 6 [1]))
        :in-decay-time [0.051]
        :in-delay-time [0.01])


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
              ;(overtone.osc/osc-send oc "/cutter/set-float" "iFloat4" val)
                (overtone.osc/osc-send oc "/cutter/i-buf" "tieto2" (int 0))
              )
            :nhtrg)

(remove-event-handler :nhtrg)


(on-trigger (get-trigger-vol-id :nh-vol)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat4" val)
                ))
            :nh-vol)

(remove-event-handler :nh-vol)


;(osc/osc-send oc "/cutter/set-trigger" (get-trigger-vol-id :nh) "iFloat4")

;(osc/osc-send oc "/cutter/unset-trigger" (get-trigger-vol-id :nh) "iFloat4")


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
                   (evr 2 acc)
                   (evr 4  [r r r ["b sn0" r r "b sn3"]])
                   ;(evr 2 fst)
                   (evr 8 [r r r r r r r ["b sn0" r "b sn2" "b sn3"]])
                   (evr 3  [[(rep "b bass23" 2) (rep r 2)] r "b bass23" r])
                   (evr 3 (fn [x] (fst x 4)))
                    )
       :in-loop [0]
       :in-buf ":in-trg"
       :in-step [1]
       :in-amp [1.5])


  (trg! :singlesmp :smpe trg-fx-echo
        :in-amp ;(evr 6 [1] (rep 32 [0]))
        (-> (rep [0.2] 6)
             (evr 6 [1]))
        :in-decay-time (-> [0.251]
                           (rep 16)
                           (rpl 16 [0.2]))
        :in-delay-time [0.1])

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
                ;(overtone.osc/osc-send oc "/cutter/set-float" "iFloat5" val)

                   ))
            :sings)

(remove-event-handler :sings)


(on-trigger (get-trigger-vol-id :singlesmp)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat5" val)
                ))
            :singlesmp-vol)

(remove-event-handler :singlesmp-vol)


;(osc/osc-send oc "/cutter/set-trigger" (get-trigger-vol-id :singlesmp) "iFloat5")

;(osc/osc-send oc "/cutter/unset-trigger" (get-trigger-vol-id :singlesmp) "iFloat5")



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
             (evr 4 [r])
             ;(evr 2 [ r 3 4 r])
             ;(rpl 1 [(rep 1 32)])
             (rpl 22 [[1 2 (rep r 6)] [r [14 50]]])
             ;(rpl 28 [1 [4 5] [(rep r 4)] 3 30  ])
             ;(evr 14 [r])
             ;(evr 16 [[2 r 3 4] r [5 6 r 7] 1 [8 9 1 2 ] r [2 3] r] )
             (evr 2 (fn [x] (fst x 2)))
             ;(evr 1 fst)
             )
       :in-f3 (-> [ "fc1" "fg1" "f f1" "fbb1"]
                  (rep 8)
                  (evr 1 sfl)
                  (evr 2  [ "fg1" "fc1" "f bb1" "ff1"])
                  (evr 4  [ "fd2" "fd3" "f c2" "ff3"])
                   )
       :in-f2 (-> [200]
                   (rep 32)
                   (evr 16 [ 2000]))
       :in-f1 (fll [1000 2000 100] 32)
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
                    ;(evr 16 [1 1 1 1 1 1 1 [(rep 1 64)]])
                    ;(rpl 33 (slw [1 1 1 1] 4))
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

  (volume! :ksmp 5.75)

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
            ;(evr 3 rev)
            ;(evr 1 fst)
            ;(evr 8 [(range  (mhz :c0)  (mhz :d3) 10)] )
            )
        (rep 2)
        (evr 4 rev))
       :in-amp [1.75]
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


(on-trigger (get-trigger-vol-id :kick)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat6" val)
                ))
            :kick-vol)

(remove-event-handler :kick-vol)


;; (def kickbus (audio-bus-monitor (get-out-bus :kick)))


;; (on-trigger (get-trigger-id :tick :in-trg)
;;             (fn [val]
;;               (let []
;;                 ;(println val)
;;                 (osc/osc-send oc "/cutter/set-float" "iFloat6" @kickbus) ))
;;             :kickbus)

;; (remove-event-handler :kickbus)


;(osc/osc-send oc "/cutter/set-trigger" (get-trigger-vol-id :kick) "iFloat6")

;(osc/osc-send oc "/cutter/unset-trigger" (get-trigger-vol-id :kick) "iFloat6")


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
                   (rep 32)
                   (evr 8 (fn [x] (fst x 3)))
                   ;(evr 4  [r r r ["b sn0" r r "b sn3"]])
                   ;(evr 8 [r r r r r r r ["b sn0" r "b sn2" "b sn3"]])
                   (evr 3  [[(rep "b bass15" 3) (rep r 5)] r])
                   (evr 6  [[(rep "b bass23" 3) (rep r 5)] r])
                    )
       :in-loop [0]
       :in-buf ":in-trg"
       :in-step [1]
       :in-amp [1.25])

  )

(play! :singlesmp)

;;;;;;;;;;;;;;;,
;;Setti 6 video
;;;;;;;;;;
;;Sormileikki



(on-trigger (get-trigger-val-id :singlesmp :in-trg)
            (fn [val]
              ;(println (int val))
              (let []
                (overtone.osc/osc-send oc "/cutter/i-buf" "sormi1" (int val))
                ;(overtone.osc/osc-send oc "/cutter/set-float" "iFloat5" val)

                   ))
            :sorm)

(remove-event-handler :sorm)




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
                ;(evr 2 acc)
                )
       :in-freq  (-> ["fc6" "fg6" "f d6" "fbb6"]
                      (rep 32)
                      (evr 2  ["fd8" [ r r "fg7" "f d5"] r "fbb7"] ))
       :in-attack (-> [0.1]
                       (rep 8)
                       (evr 8 [0.01])
                       (rpl 4 [0.001])
                       (rpl 5 [0.0001])))

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
     ;;[[(rep "b bd1" 2) r  (rep "b bd4" 1) ]  [[(rep "b sn1" 2)]  "b bd4"] [r r "b bd1" r] [ "b sn2" "b bd1"]]
    [ [(rep "b bd1" 2) r (rep "b bd4" 1) ]  [[(rep "b sn1" 1)]  "b bd4"] ["b bd1"] [ "b sn2" "b bd1"]]
    (rep 32)
    ;(evr 3  [["b bd1"]  [ "b sn1" "b bd3"] [r r (rep "b bass20" 1) r] [ "b sn2" "b bass15" r r]])
     ;(evr 9 rev)
     (evr 17 del 1 2)
     (evr 5 rpl 0  [(rep "b bd1" 2) "b sn2" (rep "b bd4" 1) ] nil )
     (evr 10 rpl 0 del 1 3 nil)
     (evr 1 rpl 2 del 2 1 nil)
     (evr 19 rpl 3 fst nil)
     (evr 3  [["b bd1"]  [ "b sn1" "b bd3"] [r r (rep "b bass20" 1) r] [ "b sn2" "b bass15" r r]])
     ;(evr 2  [["b bd1"]  [ "b sn1" "b bd3"] [r r (rep "b bass20" 1) r] [ "b sn2" "b bass15" r r]])
         ;(evr 7 sfl)
         ;(rpl 0 asc 0 [(rep "b bd1" 16)] nil)
;         (rpl 15 asc 1 [(rep "b bass15" 16)] nil)
         ;(evr 19 asc 1 fst)
         ;(evr 4 [[(rep "b bd1" 4)]   [(rep  "b sn1" 4)] [r r  "b bass23" r] [ "b sn2" r "b bd1" r]])
         ;(evr 7  ["b bd1"   (acc [(rep "b sn2" 8)]) [ "b bd2" r "b bd1" r] [ "b sn1" r "b bd1" r]])
         ;(evr 8   (fll 16 [r "b bass15"  "b sn1" r   "b bass23" r  "b sn0" r]))
         ;;(evr 15 acc)
         ;(evr 4 rev)
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
        :in-amp (-> [0.125]
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
                (overtone.osc/osc-send oc "/cutter/i-buf" "onni1" (int val))

                   ))
            :bassdtrg)

(remove-event-handler :bassdtrg)


(on-trigger (get-trigger-vol-id :softh)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat7" val)
                ))
            :softh-vol)

(remove-event-handler :softh-vol)


;; (def softhbus (audio-bus-monitor (get-out-bus :softh)))

;; (on-trigger (get-trigger-id :tick :in-trg)
;;             (fn [val]
;;               (let []
;;                 ;(println @softhbus)
;;                 (osc/osc-send oc "/cutter/set-float" "iFloat7" @softhbus) ))
;;             :softhbus)

;; (remove-event-handler :softhbus)


;(osc/osc-send oc "/cutter/set-trigger" (get-trigger-vol-id :softh) "iFloat7")


;;;;;;;;;;;,,
;; Setti 8
;;;;;;;;;;;;
(osc/osc-send oc "/cutter/set-float" "iFloat15" 7)

(pause! :bassd)
(pause! :softh)
(pause! :singlesmp)

(do
  (trg :ks1 ks1)

  (pause! :ks1)

  (trg :ks1
       ks1
       :in-trg
       [(rep "n a5" 8)]
       [(rep "n b5" 8) ]
       [(rep "n d5" 6)]
       [(rep "n e4" 2)  (rep "n c#3" 2)  (rep "n b2" 2)  (rep "n b1" 2)]
       (sfl [(fll [r r r "n b3"] 32) ])
       [(rep "n d3" 16)]
       [(rep "n a3" 16)]
       (fst ["n c#2" "n e3" "n b3" "n b2"] 2)
       ;[r]
       ;[r]
       :in-dur [10.5]
       :in-amp [1]
       :in-note ":in-trg"
       :in-decay (-> [0.75]
                     (rep 8)
                     (evr 4 [100]))
       )

  (volume! :ks1 2.5)

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
       (slw ["n b2" "n c#3" "n d2" "n d4"])
       ;[r]
                                        ;[(rep 16  "n e3")]
                                        ;[(rep 16 "n b3")]
                                        ;[(rep 16 "n c4")]
                                        ;(fst 1 ["n c#2" "n e2" "n b3" "n b4"])
       :in-gate-select (rep [0] 3) [1 0] ;(rep 4 [0])
       :in-amp [0.08]
       :in-note  ":in-trg"
       :in-a [0.01]
       :in-d [1.9]
       :in-s [2.1]
       :in-r [2.85])

  (volume! :vb 0.58)

  (trg! :vb :distro trg-fx-distortion2 :in-amount [0.97])

  (trg! :vb :reverb trg-fx-reverb :in-sig-a [0.33])

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

(sta)
(do
  (trg :tb303sn tb303)

  (pause! :tb303sn)

  (trg :tb303sn
       tb303
       :in-trg
       (->  (fst ["n c2" r r ["n c2" "n d2"]])
             ;(rep 16)
             ;(evr 2  (fst ["n e2" ["n d3"  "n c2"] r r]))
             ;(rpl 3  (fst [(rep "n e3" 2)  (rep "n c#3" 2)  (rep "n b2" 2)  (rep "n b1" 2)]))
             ;(evr 1 fst)
             ;(evr 4 rev)
             ;(evr 1 acc)
             ;(evr 3  [["n e2" "n a2" r r] [r "n d3" "n e3" "n d2"]])
             ;(evr 4  ["n d3" ["nd3" "nb2" "ne3" "na2"]])
             ;(evr 5  ["n a3" r r ["ne3" "na2" "nd3" "nc2"]])
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


  (volume! :tb303sn 5)

  )

(play! :tb303sn)


(stp :tb303sn)
;;;Setti 8 video
;;;;;;
;;;Hapsiainen



(on-trigger (get-trigger-vol-id :tb303sn)
            (fn [val]
              (let []
                ;(println val)
                (osc/osc-send oc "/cutter/set-float" "iFloat8" val)
                ))
            :tb-vol)

(remove-event-handler :tb-vol)




;(def vbbus (audio-bus-monitor (get-out-bus :tb303sn)))


;; (on-trigger (get-trigger-id :tick :in-trg)
;;             (fn [val]
;;               (let []
;;                 ;(println @softhbus)
;;                 (osc/osc-send oc "/cutter/set-float" "iFloat8" @vbbus) ))
;;             :vbbus)

;; (remove-event-handler :vbbus)

;; @vbbus


;; (osc/osc-send oc "/cutter/set-trigger" (get-trigger-vol-id :tb303sn) "iFloat8")

;;;;;;;;;;;;;;;;
;;;tmp

(stop)

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


(do
  (trg :bw bowed)

  (pause! :bw)

  (trg :bw bowed
       :in-trg (->  (fst ["n c3" r r ["n c3" "n d3"]])
                    (rep 16)
                    (evr 2  (fst ["n e3" ["n d4"  "n c3"] r r]))
                    (rpl 3  (fst [(rep "n e4" 2)  (rep "n c#4" 2)  (rep "n b3" 2)  (rep "n b1" 2)]))
                    (evr 1 fst)
                                        ;(evr 4 rev)
                    (evr 1 acc)
                                        ;(evr 3  [["n e2" "n a2" r r] [r "n d3" "n e3" "n d2"]])
                                        ;(evr 4  ["n d3" ["nd3" "nb2" "ne3" "na2"]])
                                        ;(evr 5  ["n a3" r r ["ne3" "na2" "nd3" "nc2"]])
                                        ;(evr 1 slw)
                                        ;(evr 2 slw)
                                        ;(evr 4 rev)
                                        ;(evr 3 fst)
                    )
       :in-amp [1]
       :in-note  ":in-trg")

  (volume! :bw 4))



(do
  (trg :simp smp)
  (pause! :simp)
  (trg :simp smp
       :in-trg (-> [[(fll 8  ["b bd4" "b bass23"]) (rep r 4)] r r r]
                   (fst 16)
                   (rep 32)
                   (evr 2 asc 3 [(rep "b sn0" 64)])
                   (evr 3 asc 3 [(fll 8 ["b bass23" "b bass21"])])
                   (evr 17 dcl)
                   (evr 19 acc)
                   (evr 11 fst)
                   (rpl 3 [(rep "b k1" 8)])
                   (evr 15 rev)
                   (evr 22 (fn [x] (slw x 4)))
                   ;(evr 1 (fn [x] (fst x 4)))
                  )
       :in-loop (rep  [1] 8) ;[1] (rep [0] 7)
       :in-buf ":in-trg"
       :in-step  (rep [2] 8) ;[2] (rep [2] 7)
       :in-amp [0.95])

  (trg! :simp :simpd trg-fx-distortion2 :in-amount [0.63] )


  )

(play! :simp)
