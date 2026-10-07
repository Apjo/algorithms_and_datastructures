# problem link: https://codezym.com/question/7-design-a-parking-lot
# user should be able to park, unpark their vehicle. and pay for the parked time
# should handle multiple vehicle types
# will have multiple parking statuses
# Each parking floor will have multiple parking spots for various vehicle types. And their status
# For now assume each parking floor will have same number of spots open to park
# Parking fee will be calculated upon entry and exit, i.e. duration of parking
# Will need to calculate parking fare. is there a default free or a default charge irrespective of time parked? could ask the interviewer.
# Do we need to have some parking spots reserved for handicapped vehicles, or service vehicles etc?

from enum import Enum


class VehicleType(Enum):
    BIKE = "bike"
    CAR = "car"
    TRUCK = "truck"


class ParkingStatus(Enum):
    AVAILABLE = 1
    OCCUPIED = 2


class ParkingSpotSize(Enum):
    SMALL = [VehicleType.BIKE]
    MEDIUM = [VehicleType.BIKE, VehicleType.CAR]
    LARGE = [VehicleType.BIKE, VehicleType.CAR, VehicleType.TRUCK]

    def is_vehicle_parking_possible(self, VehicleType) -> bool:
        # self.value refers to the list of vehicle types assigned to the enum member
        return VehicleType.get_type() in self.value


class Vehicle:
    def __init__(self, lic_plate: str, veh_type: VehicleType):
        self._lic_plate = lic_plate
        self._veh_type = veh_type


class ParkingSpot:
    # has details of which vehicle is parked on which spot, also determine if a vehicle of a particular type can park on this spot or not.
    def __init__(self, vehicle_type: VehicleType):
        self._vehicle = None
        self._allowed_vehicle_type = vehicle_type
        self._park_status = ParkingStatus.AVAILABLE

    def park(self, vehicle):
        self._vehicle = vehicle
        self._park_status = ParkingStatus.OCCUPIED

    def unpark(self):
        self._vehicle = None
        self._park_status = ParkingStatus.AVAILABLE

    def is_spot_occupied(self):
        return self._park_status == ParkingStatus.OCCUPIED


class ParkingFloor:
    # will have methods to park, unpark, determine avaialable parking spots for each vehicle type
    def __init__(self, floor_num: int):
        self._floor_num = floor_num
        self.parking_spots: list[ParkingSpot] = []

    def find_available_parking_spot(self, veh_type: VehicleType):
        for spot in self.parking_spots:
            # if spot does not currently have any parked vehicle, and allows such vechicle type it is available
            if not spot.is_spot_occupied and spot._allowed_vehicle_type == veh_type:
                return spot
        return None


import datetime


class Ticket:
    def __init__(self, license_plate: str, spot: ParkingSpot):
        self._lic_plate = license_plate
        self._spot = spot
        self._park_start_time = datetime.datetime.now()


class ParkingLot:
    # initializes the number of ParkingFloor,
    def __init__(self, name: str):
        self._name = name
        self._floors: list[ParkingFloor] = []
        self._ticket_info = {}

        self.hourly_rate = {
            VehicleType.BIKE: 10,
            VehicleType.CAR: 20,
            VehicleType.TRUCK: 30,
        }

    def park_vehicle(self, veh: Vehicle):
        # loop through all floors in seq
        for floor in self._floors:
            avail_parking_spot = floor.find_available_parking_spot(veh._veh_type)
            if avail_parking_spot:
                # assign/park this vehicle on the spot
                avail_parking_spot.park(veh)
                # issue a ticket
                new_tkt = Ticket(veh._lic_plate, avail_parking_spot)
                # update the ticket info map!
                self._ticket_info[veh._lic_plate] = new_tkt
                return True
        return False

    def unpark_vehicle(self, veh: Vehicle):
        # if the lic plate isn't in our ticket info, return 0.0
        if veh._lic_plate not in self._ticket_info:
            return 0.0
        # else, get ticket info for this vehicle
        tkt_inf = self._ticket_info[veh._lic_plate]
        # empty the spot that was occupied by this vehicle
        tkt_inf.spot.unpark()
        # Calculate payment (Mocking duration calculation)
        import datetime

        exit_time = datetime.datetime.now()
        duration_hours = max(1, (exit_time - tkt_inf.entry_time).seconds // 3600)
        fare = duration_hours * self.hourly_rate[tkt_inf.vehicle.vehicle_type]

        return fare
