public class Lift {
    Direction direction;
    int currentFloor;
    int floorCount;
    /**
     *  false represents going down
     *  true represents going up
     *  null represents button is not pressed for that particular floor
     */
    Boolean[] requests;


    public Lift(Direction direction, int currentFloor, int floorCount ){
        this.direction =direction;
        this.currentFloor = currentFloor;
        this.floorCount = floorCount;
        requests = new Boolean[floorCount + 1];
        for(int i = 0; i <= floorCount; i++){
            requests[i] = null;
        }
    }

    public void addRequest(Request request){
        System.out.println("Received a request: "+ request);

        if( request.floor < 0 || request.floor > floorCount){
            System.out.println("Invalid request received");
            return;
        }

        if( request.direction == Direction.DOWN){
            requests[request.floor] = false;
        }else if( request.direction == Direction.UP){
            requests[request.floor] = true;
        }else{
            System.out.println("Invalid request received");
        }

        run();
    }

    public void stop(){
        System.out.println("Stopping on floor number -> " + currentFloor);
    }

    private int getNextFloor(){
        int requestCount = 0;
        for(int i = 0; i <= floorCount; i++){
            if( requests[i] != null){
                requestCount++;
            }
        }

        if( requestCount == 0){
            return currentFloor;
        }

        if( direction == Direction.DOWN){
            if( currentFloor == 0){
                direction = Direction.UP;
                return 1;
            }else{
                return currentFloor - 1;
            }
        }else{
            if( currentFloor == floorCount){
                direction = Direction.DOWN;
                return floorCount - 1;
            }else{
                return currentFloor + 1;
            }
        }
    }

    public void run(){
        while(true){
            int nextFloor = getNextFloor();
            if(nextFloor == currentFloor){
                break;
            }
            currentFloor = nextFloor;
            if( requests[nextFloor] == null){
                System.out.println("Skipping floor as no input pressed for -> " + nextFloor );
            }else {
                if ((requests[nextFloor] == false && direction == Direction.UP) || (requests[nextFloor] == true && direction == Direction.DOWN)) {
                    System.out.println("Skipping floor as lift going in oposite direction for -> " + nextFloor);
                }

                if ((requests[nextFloor] == false && direction == Direction.DOWN) || (requests[nextFloor] == true && direction == Direction.UP)) {
                    stop();
                    requests[nextFloor] = null;
                }
            }
        }
    }
}
