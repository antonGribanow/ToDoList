//
//  TableViewController.swift
//  ToDoList
//
//  Created by Anton Gribanow on 12/01/2022.
//

import UIKit
import CoreData

class TableViewController: UITableViewController {
    
    var tasks: [Task] = []
    
    @IBAction func saveTask(_ sender: UIBarButtonItem) {
        
        let alertController = UIAlertController(title: "New Task", message: "Please add a new task", preferredStyle: .alert)
            
            
            let saveAction = UIAlertAction(title: "Save", style: .default) { action in
                let tf = alertController.textFields?.first
                if let newTaskTitle = tf?.text{
                    self.saveTask(withTitle: newTaskTitle)
                    self.tableView.reloadData()
                }
            }
        
        let cancelAction = UIAlertAction(title: "Cancel", style: .default) { _ in }
        
        alertController.addTextField { _ in }
        
        alertController.addAction(saveAction)
        alertController.addAction(cancelAction)
        
        present(alertController, animated: true)
        
        
        }
    
    
    
        
        private func saveTask(withTitle title: String) {        //Метода сохраняет в кор дата
            let appDeligate = UIApplication.shared.delegate as! AppDelegate
            let context = appDeligate.persistentContainer.viewContext       //добираемся до контекста
            
            guard let entity = NSEntityDescription.entity(forEntityName: "Task", in: context) else { return } //добираемся до сущнеости
            
            let taskObject = Task(entity: entity, insertInto: context)                           //получаем Task обьект
            taskObject.title = title                                                      //помещаем загшоловок в Task обьект
            
            do{
                try context.save()
                
            }catch let error as NSError{
                print(error.localizedDescription)
            }
        }
        
    }
    
    
    

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return tasks.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        
        let task = tasks[indexPath.row]
        cell.textLabel?.text = task.title

        return cell
    }
    

    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    

    

}
